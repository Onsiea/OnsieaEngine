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
package fr.onsiea.engine.client.graphics.texture;

import java.nio.ByteBuffer;

import fr.onsiea.engine.client.resources.IResourcesPath;
import fr.onsiea.engine.utils.registry.ILoadable;
import fr.onsiea.engine.utils.registry.IRegistry;

/**
 * @author Seynax
 *
 */
public interface ITexturesManager extends IRegistry<ITexture>, ILoadable<ITexture>
{
	ITexture load(String nameIn, ByteBuffer pixelsIn, int widthIn, int heightIn);

	/**
	 * @param nameIn
	 * @param minIn
	 * @param magIn
	 * @param wrapSIn
	 * @param wrapTIn
	 * @param mipmappingIn
	 * @return Texture
	 */
	ITexture load(String nameIn, int minIn, int magIn, int wrapSIn, int wrapTIn, boolean mipmappingIn);

	/**
	 * @param nameIn
	 * @param resourcesPathIn
	 * @return Texture
	 * @throws Exception
	 */
	ITexture loadCubeMapTextures(String nameIn, IResourcesPath... resourcesPathIn) throws Exception;

	/**
	 * @param nameIn
	 * @param minIn
	 * @param magIn
	 * @param wrapSIn
	 * @param wrapTIn
	 * @param mipmappingIn
	 * @param resourcesPathIn
	 * @return Texture
	 * @throws Exception
	 */
	ITexture loadCubeMapTextures(String nameIn, int minIn, int magIn, int wrapSIn, int wrapTIn, boolean mipmappingIn,
			IResourcesPath... resourcesPathIn) throws Exception;

	/**
	 *
	 * @param nameIn
	 * @param widthIn
	 * @param heightIn
	 * @param pixelFormatIn
	 * @return Texture
	 */
	ITexture createEmpty(String nameIn, int widthIn, int heightIn, int pixelFormatIn);

	/**
	 *
	 * @param nameIn
	 * @param widthIn
	 * @param heightIn
	 * @param pixelFormatIn
	 * @param minIn
	 * @param magIn
	 * @param wrapSIn
	 * @param wrapTIn
	 * @param mipmappingIn
	 * @return Texture
	 */
	ITexture createEmpty(String nameIn, int widthIn, int heightIn, int pixelFormatIn, int minIn, int magIn, int wrapSIn,
			int wrapTIn, boolean mipmappingIn);
}