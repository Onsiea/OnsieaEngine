package fr.onsiea.engine.client.graphics.opengl.shader.uniform;

import org.lwjgl.opengl.GL20;

import fr.onsiea.engine.client.graphics.opengl.shader.GLShaderProgram;
import fr.onsiea.engine.client.graphics.shader.IShaderProgram;
import fr.onsiea.engine.client.graphics.shader.IShaderUniform;
import lombok.NonNull;

public class GLUniformBoolean implements IShaderUniform<Boolean>
{
	public final static void load(int locationIn, boolean valueIn)
	{
		GL20.glUniform1f(locationIn, valueIn ? 1.0f : 0.0f);
	}

	private IShaderProgram	parent;
	private int				location;

	public GLUniformBoolean(GLShaderProgram parentIn, String nameIn)
	{
		this.parent(parentIn);

		this.location(parentIn.uniformLocation(nameIn));
	}

	@Override
	@NonNull
	public IShaderProgram load(Boolean valueIn)
	{
		GL20.glUniform1f(this.location(), valueIn ? 1.0f : 0.0f);

		return this.parent();
	}

	private final IShaderProgram parent()
	{
		return this.parent;
	}

	private final void parent(IShaderProgram parentIn)
	{
		this.parent = parentIn;
	}

	private final int location()
	{
		return this.location;
	}

	private final void location(int locationIn)
	{
		this.location = locationIn;
	}
}