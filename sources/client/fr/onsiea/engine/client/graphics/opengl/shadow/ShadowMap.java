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
package fr.onsiea.engine.client.graphics.opengl.shadow;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

import fr.onsiea.engine.client.graphics.texture.ITexturesManager;

/**
 * @author Seynax
 *
 */
public class ShadowMap
{
	public static final int	SHADOW_MAP_WIDTH	= 1024;

	public static final int	SHADOW_MAP_HEIGHT	= 1024;

	private final int		depthMapFBO;

	private final int		depthMap;

	public ShadowMap(ITexturesManager texturesManagerIn) throws Exception
	{
		// Create a FBO to render the depth map
		this.depthMapFBO	= GL30.glGenFramebuffers();

		// Create the depth map texture
		//this.depthMap		= texturesManagerIn.createEmpty("shadowMap", ShadowMap.SHADOW_MAP_WIDTH,
		//		ShadowMap.SHADOW_MAP_HEIGHT, GL11.GL_DEPTH_COMPONENT, GL11.GL_NEAREST, GL11.GL_NEAREST,
		//		GL12.GL_CLAMP_TO_EDGE, GL12.GL_CLAMP_TO_EDGE, false);
		this.depthMap		= GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.depthMap);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT24, ShadowMap.SHADOW_MAP_WIDTH,
				ShadowMap.SHADOW_MAP_HEIGHT, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_BORDER);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_BORDER);
		final float borderColor[] =
		{ 1.0f, 1.0f, 1.0f, 1.0f };
		GL11.glTexParameterfv(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_BORDER_COLOR, borderColor);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_FUNC, GL11.GL_LEQUAL);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_MODE, GL14.GL_COMPARE_R_TO_TEXTURE);

		// Attach the the depth map texture to the FBO
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.depthMapFBO);
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, this.depthMap,
				0);
		// Set only depth
		GL11.glDrawBuffer(GL11.GL_NONE);
		GL11.glReadBuffer(GL11.GL_NONE);

		if (GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER) != GL30.GL_FRAMEBUFFER_COMPLETE)
		{
			throw new Exception("Could not create FrameBuffer");
		}

		// Unbind
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}

	public int depthMapTexture()
	{
		return this.depthMap;
	}

	public int depthMapFBO()
	{
		return this.depthMapFBO;
	}

	public void cleanup()
	{
		GL30.glDeleteFramebuffers(this.depthMapFBO);
	}
}