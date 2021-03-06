/**
*	Copyright 2021 Onsiea All rights reserved.
*
*	This file is part of Onsiea Engine. (https://github.com/Onsiea/OnsieaEngine)
*
*	Unless noted in license (https://github.com/Onsiea/OnsieaEngine/blob/main/LICENSE.md) notice file (https://github.com/Onsiea/OnsieaEngine/blob/main/LICENSE_NOTICE.md), Onsiea engine and all parts herein is licensed under the terms of the LGPL-3.0 (https://www.gnu.org/licenses/lgpl-3.0.html)  found here https://www.gnu.org/licenses/lgpl-3.0.html and copied below the license file.
*
*	Onsiea Engine is libre software: you can redistribute it and/or modify
*	it under the terms of the GNU Lesser General Public License as published by
*	the Free Software Foundation, either version 3.0 of the License, or
*	(at your option) any later version.
*
*	Onsiea Engine is distributed in the hope that it will be useful,
*	but WITHOUT ANY WARRANTY; without even the implied warranty of
*	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*	GNU Lesser General Public License for more details.
*
*	You should have received a copy of the GNU Lesser General Public License
*	along with Onsiea Engine.  If not, see <https://www.gnu.org/licenses/> <https://www.gnu.org/licenses/lgpl-3.0.html>.
*
*	Neither the name "Onsiea", "Onsiea Engine", or any derivative name or the names of its authors / contributors may be used to endorse or promote products derived from this software and even less to name another project or other work without clear and precise permissions written in advance.
*
*	(more details on https://github.com/Onsiea/OnsieaEngine/wiki/License)
*
*	@author Seynax
*/
package fr.onsiea.engine.client.graphics;

import fr.onsiea.engine.client.graphics.render.EnumRenderAPI;
import fr.onsiea.engine.client.resources.IResourcesPath;
import fr.onsiea.engine.client.resources.ResourcesRootPath;

/**
 * @author Seynax
 *
 */

public class GraphicsConstants
{
	public final static boolean			DEBUG					= false;
	public static final boolean			SHADER_UNIFORM_DEBUG	= false;

	public final static int				DEFAULT_WIDTH			= 1920;
	public final static int				DEFAULT_HEIGHT			= 1080;

	public final static int				DEFAULT_REFRESH_RATE	= 60;
	public final static String			DEFAULT_WINDOW_TITLE	= "Onsiea";

	public final static IResourcesPath	TEXTURES				= new ResourcesRootPath("resources\\textures");
	public final static IResourcesPath	SHADERS					= new ResourcesRootPath("resources\\shaders");
	public final static IResourcesPath	FONTS					= new ResourcesRootPath("resources\\fonts");

	/**
	 * Render
	 */

	public final static float			FOV						= 70.0f;
	public final static float			ZNEAR					= 0.01f;
	public final static float			ZFAR					= 1200.0f;

	public final static EnumRenderAPI	RENDER_API				= EnumRenderAPI.OpenGL;

	public static final boolean			CULL_FACE				= false;
	public static final boolean			SHOW_TRIANGLES			= false;
}