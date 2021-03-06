/**
* Copyright 2021 Onsiea All rights reserved.<br><br>
*
* This file is part of Onsiea Engine project. (https://github.com/Onsiea/OnsieaEngine)<br><br>
*
* Onsiea Engine is [licensed] (https://github.com/Onsiea/OnsieaEngine/blob/main/LICENSE) under the terms of the "GNU General Public Lesser License v3.0" (GPL-3.0).
* https://github.com/Onsiea/OnsieaEngine/wiki/License#license-and-copyright<br><br>
*
* Onsiea Engine is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3.0 of the License, or
* (at your option) any later version.<br><br>
*
* Onsiea Engine is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.<br><br>
*
* You should have received a copy of the GNU Lesser General Public License
* along with Onsiea Engine.  If not, see <https://www.gnu.org/licenses/>.<br><br>
*
* Neither the name "Onsiea", "Onsiea Engine", or any derivative name or the names of its authors / contributors may be used to endorse or promote products derived from this software and even less to name another project or other work without clear and precise permissions written in advance.<br><br>
*
* @Author : Seynax (https://github.com/seynax)<br>
* @Organization : Onsiea Studio (https://github.com/Onsiea)
*/
package fr.onsiea.engine.client.graphics.opengl;

import java.util.Collection;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GLDebugMessageCallback;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.system.MemoryUtil;

import fr.onsiea.engine.client.graphics.GraphicsConstants;
import fr.onsiea.engine.client.graphics.mesh.IMeshsManager;
import fr.onsiea.engine.client.graphics.mesh.obj.normalMapped.NormalMappedObjLoader;
import fr.onsiea.engine.client.graphics.opengl.mesh.GLMeshManager;
import fr.onsiea.engine.client.graphics.opengl.shader.manager.GLShaderManager;
import fr.onsiea.engine.client.graphics.opengl.texture.GLTexture;
import fr.onsiea.engine.client.graphics.opengl.texture.GLTextureCubeMap;
import fr.onsiea.engine.client.graphics.opengl.texture.GLTexturesManager;
import fr.onsiea.engine.client.graphics.opengl.utils.OpenGLUtils;
import fr.onsiea.engine.client.graphics.render.IRenderAPIContext;
import fr.onsiea.engine.client.graphics.render.IRenderAPIMethods;
import fr.onsiea.engine.client.graphics.shader.IShadersManager;
import fr.onsiea.engine.client.graphics.texture.ITexture;
import fr.onsiea.engine.client.graphics.texture.ITextureData;
import fr.onsiea.engine.client.graphics.texture.ITexturesManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Seynax
 *
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PRIVATE)
public class OpenGLRenderAPIContext implements IRenderAPIContext, IRenderAPIMethods
{
	public final static OpenGLRenderAPIContext create() throws IllegalStateException, Exception
	{
		return new OpenGLRenderAPIContext(OpenGLInitializer.initialize());
	}

	private GLCapabilities			capabilities;
	private GLDebugMessageCallback	debugProc;
	private GLDebugMessageCallback	openGLDebug;

	private OpenGLSettings			settings;

	private ITexturesManager		texturesManager;
	private IMeshsManager			meshsManager;
	private IShadersManager			shadersManager;

	/**
	 *
	 * @param capabilitiesIn
	 * @throws Exception
	 */
	private OpenGLRenderAPIContext(GLCapabilities capabilitiesIn) throws Exception
	{
		this.capabilities(capabilitiesIn);
		this.settings(new OpenGLSettings(this));

		if (GraphicsConstants.DEBUG)
		{
			this.enableDebugging();
		}
		else
		{
			this.disableDebugging();
		}

		this.settings().user().enable("mustAnisotropyTextureFiltering").set("anisotropyTextureFilteringAmount", 4.0f);

		this.shadersManager(new GLShaderManager());
		this.texturesManager(new GLTexturesManager(this));

		this.meshsManager(new GLMeshManager(new NormalMappedObjLoader()));

		// Set the clear color
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		//glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );

		// Support for transparencies
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}

	@Override
	public ITexture createTexture(ITextureData textureDataIn)
	{
		return new GLTexture(textureDataIn, this);
	}

	@Override
	public ITexture createTexture(ITextureData textureDataIn, int minIn, int magIn, int wrapSIn, int wrapTIn,
			boolean mipmappingIn)
	{
		return new GLTexture(textureDataIn, this, minIn, magIn, wrapSIn, wrapTIn, mipmappingIn);
	}

	@Override
	public ITexture createTexture(int widthIn, int heightIn, int pixelFormatIn)
	{
		return new GLTexture(widthIn, heightIn, pixelFormatIn, this);
	}

	@Override
	public ITexture createTexture(int widthIn, int heightIn, int pixelFormatIn, int minIn, int magIn, int wrapSIn,
			int wrapTIn, boolean mipmappingIn)
	{
		return new GLTexture(widthIn, heightIn, pixelFormatIn, this, minIn, magIn, wrapSIn, wrapTIn, mipmappingIn);
	}

	@Override
	public ITexture createCubeMapTextures(ITextureData... texturesDataIn) throws Exception
	{
		return this.createCubeMapTextures(GL11.GL_LINEAR_MIPMAP_LINEAR, GL11.GL_LINEAR, GL12.GL_CLAMP_TO_EDGE,
				GL12.GL_CLAMP_TO_EDGE, true, texturesDataIn);
	}

	@Override
	public ITexture createCubeMapTextures(int minIn, int magIn, int wrapSIn, int wrapTIn, boolean mipmappingIn,
			ITextureData... texturesDataIn) throws Exception
	{
		final var texture = GL11.glGenTextures();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture);

		for (var i = 0; i < texturesDataIn.length; i++)
		{
			final var textureData = texturesDataIn[i];
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA, textureData.width(),
					textureData.height(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureData.buffer());

			if (!textureData.cleanup())
			{
				throw new Exception("[ERROR] Unable to unload buffer of textures cube map data !");
			}
		}

		return new GLTextureCubeMap(texture, this, minIn, magIn, wrapSIn, wrapTIn, mipmappingIn);
	}

	/**
	 * @return
	 */
	public boolean textureFilterAnisotropicIsCompatible()
	{
		return this.capabilities.GL_EXT_texture_filter_anisotropic;
	}

	public void enableDebugging()
	{
		// GLFW

		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_DEBUG_CONTEXT, GLFW.GLFW_TRUE);

		// Callback

		this.openGLDebug(new OpenGLDebug());

		// this.debugProc(GLUtil.setupDebugMessageCallback());
		this.debugProc(GLDebugMessageCallback.create(this.openGLDebug()));
		KHRDebug.glDebugMessageCallback(this.openGLDebug(), 0);

		// GL

		GL11.glEnable(GL43.GL_DEBUG_OUTPUT);
		GL11.glEnable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS);
		// GL11.glEnable(KHRDebug.GL_DEBUG_OUTPUT);
	}

	public void disableDebugging()
	{
		// GL

		GL11.glDisable(GL43.GL_DEBUG_OUTPUT);
		GL11.glDisable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS);
		//GL11.glDisable(KHRDebug.GL_DEBUG_OUTPUT);

		// GLFW

		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_DEBUG_CONTEXT, GLFW.GLFW_FALSE);

		// Callback

		if (this.openGLDebug != null)
		{
			this.openGLDebug().free();
			this.openGLDebug(null);
			// this.debugProc().free();
			this.debugProc(null);
		}

		KHRDebug.glDebugMessageCallback(null, 0);
	}

	@Override
	public void cleanup()
	{
		OpenGLUtils.restoreState();

		this.meshsManager().cleanup();
		// OpenGLScreenshot.cleanup();
		// this.shaderManager().cleanup();
		this.disableDebugging();

		GL.destroy();
		GL.setCapabilities(null);
	}

	@Override
	public void deleteTextures(Collection<ITexture> valuesIn)
	{
		final var texturesBuffer = MemoryUtil.memAllocInt(valuesIn.size());

		for (final ITexture texture : valuesIn)
		{
			texture.detach();

			texturesBuffer.put(texture.id());

		}

		texturesBuffer.flip();

		GLTexture.deletes(texturesBuffer);

		MemoryUtil.memFree(texturesBuffer);
	}
}