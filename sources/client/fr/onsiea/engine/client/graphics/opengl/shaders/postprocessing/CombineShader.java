package fr.onsiea.engine.client.graphics.opengl.shaders.postprocessing;

import fr.onsiea.engine.client.graphics.opengl.shader.GLShaderProgram;
import fr.onsiea.engine.client.graphics.shader.uniform.IShaderTypedUniform;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
public class CombineShader extends GLShaderProgram
{
	private static final String					VERTEX_FILE		= "resources/shaders/postprocessing/combine/combineVertex.glsl";
	private static final String					FRAGMENT_FILE	= "resources/shaders/postprocessing/combine/combineFragment.glsl";

	private final IShaderTypedUniform<Integer>	uniformColourTexture;
	private final IShaderTypedUniform<Integer>	uniformHighlightTexture2;
	private final IShaderTypedUniform<Integer>	uniformHighlightTexture4;
	private final IShaderTypedUniform<Integer>	uniformHighlightTexture8;

	public CombineShader() throws Exception
	{
		super("combineFilter", CombineShader.VERTEX_FILE, CombineShader.FRAGMENT_FILE, "position");

		this.uniformColourTexture		= this.intUniform("colourTexture");
		this.uniformHighlightTexture2	= this.intUniform("highlightTexture2");
		this.uniformHighlightTexture4	= this.intUniform("highlightTexture4");
		this.uniformHighlightTexture8	= this.intUniform("highlightTexture8");

		this.attach();
		this.connectTextureUnits();
	}

	private void connectTextureUnits()
	{
		this.uniformColourTexture.load(0);
		this.uniformHighlightTexture2.load(1);
		this.uniformHighlightTexture4.load(2);
		this.uniformHighlightTexture8.load(3);
	}
}