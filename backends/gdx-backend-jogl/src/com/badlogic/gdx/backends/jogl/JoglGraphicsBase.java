/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.backends.jogl;

import java.awt.Color;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.GLU;

public abstract class JoglGraphicsBase implements Graphics, GLEventListener {
	static int major, minor;

	GLCanvas canvas;
	JoglAnimator animator;
	boolean useGL2;
	long frameStart = System.nanoTime();
	long lastFrameTime = System.nanoTime();
	float deltaTime = 0;
	int fps;
	int frames;
	boolean paused = true;

	GLCommon gl;
	GL10 gl10;
	GL11 gl11;
	GL20 gl20;
	GLU glu;

	void initialize (JoglApplicationConfiguration config) {
		GLCapabilities caps = new GLCapabilities();
		caps.setRedBits(config.r);
		caps.setGreenBits(config.g);
		caps.setBlueBits(config.b);
		caps.setAlphaBits(config.a);
		caps.setDepthBits(config.depth);
		caps.setStencilBits(config.stencil);
		caps.setNumSamples(config.samples);
		caps.setSampleBuffers(config.samples > 0);
		caps.setDoubleBuffered(true);

		canvas = new GLCanvas(caps);
		canvas.setBackground(Color.BLACK);
		canvas.addGLEventListener(this);
		this.useGL2 = config.useGL20;
		this.glu = new JoglGLU();
		Gdx.glu = glu;

	}

	GLCanvas getCanvas () {
		return canvas;
	}

	void create () {
		frameStart = System.nanoTime();
		lastFrameTime = frameStart;
		deltaTime = 0;
		animator = new JoglAnimator(canvas);
		animator.start();
	}

	void pause () {
		synchronized (this) {
			paused = true;
		}
		animator.stop();
	}

	void resume () {
		paused = false;
		frameStart = System.nanoTime();
		lastFrameTime = frameStart;
		deltaTime = 0;
		animator = new JoglAnimator(canvas);
		animator.setRunAsFastAsPossible(true);
		animator.start();
	}

	void initializeGLInstances (GLAutoDrawable drawable) {
		String version = drawable.getGL().glGetString(GL.GL_VERSION);
		String renderer = drawable.getGL().glGetString(GL.GL_RENDERER);
		major = Integer.parseInt("" + version.charAt(0));
		minor = Integer.parseInt("" + version.charAt(2));

		if (useGL2 && major >= 2) {
			gl20 = new JoglGL20(drawable.getGL());
			gl = gl20;
		} else {

			if (major == 1 && minor < 5 || renderer.equals("Mirage Graphics3")) {
				gl10 = new JoglGL10(drawable.getGL());
			} else {
				gl11 = new JoglGL11(drawable.getGL());
				gl10 = gl11;
			}
			gl = gl10;
		}

		Gdx.gl = gl;
		Gdx.gl10 = gl10;
		Gdx.gl11 = gl11;
		Gdx.gl20 = gl20;
	}

	void updateTimes () {
		deltaTime = (System.nanoTime() - lastFrameTime) / 1000000000.0f;
		lastFrameTime = System.nanoTime();

		if (System.nanoTime() - frameStart > 1000000000) {
			fps = frames;
			frames = 0;
			frameStart = System.nanoTime();
		}
		frames++;
	}

	@Override
	public float getDeltaTime () {
		return deltaTime;
	}

	@Override
	public float getRawDeltaTime () {
		return deltaTime;
	}

	@Override
	public int getFramesPerSecond () {
		return fps;
	}

	@Override
	public int getHeight () {
		return canvas.getHeight();
	}

	@Override
	public int getWidth () {
		return canvas.getWidth();
	}

	@Override
	public GL10 getGL10 () {
		return gl10;
	}

	@Override
	public GL11 getGL11 () {
		return gl11;
	}

	@Override
	public GL20 getGL20 () {
		return gl20;
	}

	@Override
	public GLCommon getGLCommon () {
		return gl;
	}

	@Override
	public GLU getGLU () {
		return glu;
	}

	@Override
	public boolean isGL11Available () {
		return gl11 != null;
	}

	@Override
	public boolean isGL20Available () {
		return gl20 != null;
	}

	@Override
	public GraphicsType getType () {
		return GraphicsType.JoglGL;
	}

	@Override
	public void setContinuousRendering (boolean isContinuous) {
		animator.setContinuousRendering(isContinuous);
	}

	@Override
	public boolean isContinuousRendering () {
		return animator.isContinuousRendering();
	}

	@Override
	public void requestRendering () {
		animator.requestRendering();
	}
}
