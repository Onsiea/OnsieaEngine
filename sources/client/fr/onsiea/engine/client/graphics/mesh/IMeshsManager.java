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
package fr.onsiea.engine.client.graphics.mesh;

import fr.onsiea.engine.client.graphics.mesh.obj.IOBJLoader;
import fr.onsiea.engine.utils.registry.ILoadable;
import fr.onsiea.engine.utils.registry.IRegistry;

/**
 * @author Seynax
 *
 */
public interface IMeshsManager extends IRegistry<IMesh>, ILoadable<IMesh>
{
	/**
	 * @param posArrIn
	 * @param textCoordArrIn
	 * @param normArrIn
	 * @param indicesArrIn
	 * @return
	 * @throws Exception
	 */
	IMesh create(float[] positionsIn, float[] texturesCoordinatesIn, float[] normalsIn, int[] indicesIn,
			int dimensionSizeIn) throws Exception;

	/**
	 * @param posArrIn
	 * @param textCoordArrIn
	 * @param normArrIn
	 * @param indicesArrIn
	 * @return
	 * @throws Exception
	 */
	IMesh create(float[] positionsIn, int dimensionSizeIn) throws Exception;

	/**
	 * @param withSizeIn
	 * @param indicesIn
	 * @param iIn
	 * @return
	 * @throws Exception
	 */
	IMesh create(float[] positionsIn, int[] indicesIn, int dimensionSizeIn) throws Exception;

	/**
	 * @param positionsIn
	 * @param texturesCoordinatesIn
	 * @param normalsIn
	 * @param tangentsArrayIn
	 * @param indicesIn
	 * @param dimensionSizeIn
	 * @return
	 * @throws Exception
	 */
	IMesh create(float[] positionsIn, float[] texturesCoordinatesIn, float[] normalsIn, float[] tangentsArrayIn,
			int[] indicesIn, int dimensionSizeIn) throws Exception;

	IOBJLoader objLoader();

	/**
	 * @param positionsIn
	 * @param texturesCoordinatesIn
	 * @param normalsIn
	 * @param indicesArrIn
	 * @param indicesIn
	 * @return
	 * @throws Exception
	 */
	IMaterialMesh createMeshWithMaterial(float[] positionsIn, float[] texturesCoordinatesIn, float[] normalsIn,
			int[] indicesIn, int dimensionIn) throws Exception;

	/**
	 * @param positionsIn
	 * @param texturesCoordinatesIn
	 * @param normalsIn
	 * @param tangentsArrayIn
	 * @param indicesIn
	 * @param dimensionIn
	 * @return
	 * @throws Exception
	 */
	IMaterialMesh createMeshWithMaterial(float[] positionsIn, float[] texturesCoordinatesIn, float[] normalsIn,
			float[] tangentsArrayIn, int[] indicesIn, int dimensionIn) throws Exception;

	/**
	 * @param positionsIn
	 * @param texturesCoordinatesIn
	 * @param normalsIn
	 * @param indicesIn
	 * @param jointIndicesIn
	 * @param weightsIn
	 * @param dimensionIn
	 * @return
	 * @throws Exception
	 */
	IMaterialMesh createMeshWithMaterial(float[] positionsIn, float[] texturesCoordinatesIn, float[] normalsIn,
			int[] indicesIn, int[] jointIndicesIn, float[] weightsIn, int dimensionIn) throws Exception;

	/**
	 * @param positionsIn
	 * @param texturesCoordinatesIn
	 * @param normalsIn
	 * @param tangentsArrayIn
	 * @param indicesIn
	 * @param jointIndicesIn
	 * @param weightsIn
	 * @param dimensionIn
	 * @return
	 * @throws Exception
	 */
	IMaterialMesh createMeshWithMaterial(float[] positionsIn, float[] texturesCoordinatesIn, float[] normalsIn,
			float[] tangentsArrayIn, int[] indicesIn, int[] jointIndicesIn, float[] weightsIn, int dimensionIn)
			throws Exception;
}