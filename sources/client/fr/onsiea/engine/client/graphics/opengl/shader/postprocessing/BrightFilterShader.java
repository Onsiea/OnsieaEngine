package fr.onsiea.engine.client.graphics.opengl.shader.postprocessing;

import fr.onsiea.engine.client.graphics.opengl.shader.Shader;

public class BrightFilterShader extends Shader
{
	private static final String	VERTEX_FILE		= "resources/shaders/postprocessing/brightfilter/brightFilterVertex.glsl";
	private static final String	FRAGMENT_FILE	= "resources/shaders/postprocessing/brightfilter/brightFilterFragment.glsl";

	public BrightFilterShader() throws Exception
	{
		super(BrightFilterShader.VERTEX_FILE, BrightFilterShader.FRAGMENT_FILE, "position");
	}
}