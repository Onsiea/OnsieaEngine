package fr.onsiea.engine.client.graphics.texture;

import java.io.File;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import fr.onsiea.engine.client.graphics.render.IRenderAPIMethods;
import fr.onsiea.engine.client.graphics.texture.data.TextureBuffer;
import fr.onsiea.engine.client.graphics.texture.data.TextureBytes;

public class TextureLoader
{
	public final static ITexture load(final String filepathIn, final TextureBytes textureBytesIn,
			IRenderAPIMethods renderAPIContextIn)
	{
		final var	realFilepath	= TextureUtils.filepath(filepathIn);

		final var	textureBuffer	= TextureBuffer.load(new File(realFilepath), false);
		if (textureBuffer != null)
		{
			final var buffer = BufferUtils.createByteBuffer(textureBytesIn.bytes().length);
			buffer.put(textureBytesIn.bytes());
			buffer.flip();

			final var texture = renderAPIContextIn.createTexture(textureBuffer.components().width(),
					textureBuffer.components().height(), buffer);

			if (!textureBuffer.cleanup())
			{
				throw new RuntimeException("[ERROR] Unable to unload texture buffer : \"" + realFilepath + "\"");
			}

			return texture;
		}

		throw new RuntimeException("[ERROR] Unable to load texture : \"" + realFilepath + "\"");
	}

	final static ITexture load(final String filepathIn, IRenderAPIMethods renderAPIContextIn)
	{
		final var realFilepath = TextureUtils.filepath(filepathIn);

		try
		{
			final var textureBuffer = TextureBuffer.load(new File(realFilepath), false);
			if (textureBuffer != null)
			{
				final var texture = renderAPIContextIn.createTexture(textureBuffer.components().width(),
						textureBuffer.components().height(), textureBuffer.buffer());

				if (!textureBuffer.cleanup())
				{
					throw new RuntimeException("[ERROR] Unable to unload texture buffer : \"" + realFilepath + "\"");
				}

				return texture;
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		throw new RuntimeException("[ERROR] Unable to load texture : \"" + realFilepath + "\"");
	}

	/**
	 * @param filepathIn
	 * @param renderAPIContextIn
	 * @param minIn
	 * @param magIn
	 * @param wrapSIn
	 * @param wrapTIn
	 * @param mipmappingIn
	 * @return
	 */
	public static ITexture load(String filepathIn, IRenderAPIMethods renderAPIContextIn, int minIn, int magIn,
			int wrapSIn, int wrapTIn, boolean mipmappingIn)
	{
		final var realFilepath = TextureUtils.filepath(filepathIn);

		try
		{
			final var textureBuffer = TextureBuffer.load(new File(realFilepath), false);
			if (textureBuffer != null)
			{
				final var texture = renderAPIContextIn.createTexture(textureBuffer.components().width(),
						textureBuffer.components().height(), textureBuffer.buffer(), minIn, magIn, wrapSIn, wrapTIn,
						mipmappingIn);

				if (!textureBuffer.cleanup())
				{
					throw new RuntimeException("[ERROR] Unable to unload texture buffer : \"" + realFilepath + "\"");
				}

				return texture;
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		throw new RuntimeException("[ERROR] Unable to load texture : \"" + realFilepath + "\"");
	}

	public final static ITexture load(final ByteBuffer bufferIn, final int widthIn, final int heightIn,
			IRenderAPIMethods renderAPIContextIn)
	{
		if (bufferIn == null)
		{
			return null;
		}

		final var texture = renderAPIContextIn.createTexture(widthIn, heightIn, bufferIn);

		STBImage.stbi_image_free(bufferIn);

		return texture;
	}

	final static void send(final ITexture textureIn, final byte[] dataIn)
	{
		final var byteBuffer = BufferUtils.createByteBuffer(dataIn.length);

		byteBuffer.put(dataIn);

		byteBuffer.flip();

		textureIn.send(byteBuffer);
	}
}