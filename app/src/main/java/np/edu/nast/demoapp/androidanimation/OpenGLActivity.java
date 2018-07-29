package np.edu.nast.demoapp.androidanimation;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLActivity extends Activity {

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new OpenGLRenderer());
        setContentView(glSurfaceView);
    }

    public static class OpenGLRenderer implements GLSurfaceView.Renderer {
        private Cube cube;

        private float rotationAngle;

        public OpenGLRenderer() {
            cube = new Cube();
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // ...
            gl.glEnable(GL10.GL_DEPTH_TEST);
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            // ...

            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();

            float fovy = 50.0f; // Field of view angle, in degrees, in the Y direction.
            float aspect = (float) width / (float) height;
            float zNear = 0.1f;
            float zFar = 100.0f;
            // Set up a perspective projection matrix
            GLU.gluPerspective(gl, fovy, aspect, zNear, zFar);

            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            // ...
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            // Replace the current matrix with the identity matrix.
            gl.glLoadIdentity();
            gl.glTranslatef(0.0f, 0.0f, -6.0f);
            gl.glScalef(0.8f, 0.8f, 0.8f);
            gl.glRotatef(rotationAngle, 1.0f, 1.0f, 1.0f);
            cube.draw(gl);
            rotationAngle -= 0.4f;
        }

        private static class Cube {

            private FloatBuffer vertexBuffer;
            private ByteBuffer drawListBuffer;
            private FloatBuffer colorBuffer;


            // Coordinates for vertices
            static float cubeCoords[] = {
                    -1.0f, -1.0f, 1.0f,  // vertex 0 (x0, y0, z0)
                    1.0f, -1.0f, 1.0f,  // vertex 1 (x1, y1, z1)
                    1.0f, 1.0f, 1.0f,  // vertex 2 (x2, y2, z2)
                    -1.0f, 1.0f, 1.0f,  // vertex 3 (x3, y3, z3)
                    -1.0f, -1.0f, -1.0f,  // and so on...
                    1.0f, -1.0f, -1.0f,
                    1.0f, 1.0f, -1.0f,
                    -1.0f, 1.0f, -1.0f
            };
            // Color definition
            private float colors[] = {
                    1.0f, 0.0f, 0.0f, 1.0f, // red for vertex 0
                    0.0f, 1.0f, 0.0f, 1.0f, // gree for vertex 1
                    0.0f, 0.0f, 1.0f, 1.0f, // blue for vertex 2
                    1.0f, 1.0f, 0.0f, 1.0f, // yellow for vertex 3
                    1.0f, 1.0f, 0.0f, 1.0f, // and so on...
                    0.0f, 0.0f, 1.0f, 1.0f,
                    0.0f, 1.0f, 0.0f, 1.0f,
                    1.0f, 0.0f, 0.0f, 1.0f
            };
            // Drawing order of cubeCoords[]
            private byte drawOrder[] = {
                    0, 1, 3, 1, 3, 2,
                    1, 2, 6, 1, 6, 5,
                    0, 3, 7, 0, 7, 4,
                    4, 7, 6, 4, 6, 5,
                    3, 7, 2, 7, 2, 6,
                    0, 4, 1, 4, 1, 5
            };

            public Cube() {

                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(cubeCoords.length * 4);
                byteBuffer.order(ByteOrder.nativeOrder());

                vertexBuffer = byteBuffer.asFloatBuffer();
                vertexBuffer.put(cubeCoords);
                vertexBuffer.position(0);

                byteBuffer = ByteBuffer.allocateDirect(colors.length * 4);
                byteBuffer.order(ByteOrder.nativeOrder());
                colorBuffer = byteBuffer.asFloatBuffer();
                colorBuffer.put(colors);
                colorBuffer.position(0);

                drawListBuffer = ByteBuffer.allocateDirect(drawOrder.length);
                drawListBuffer.put(drawOrder);
                drawListBuffer.position(0);

            }

            public void draw(GL10 gl) {
                // Enable vertex array buffer to be used during rendering
                gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
                // Tell openGL where the vertex array buffer is
                gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);


                // Enable color array buffer to be used during rendering
                gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
                // Tell openGL where the color array buffer is
                gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

                // Draw each plane as a pair of triangles, based on the drawListBuffer information
                gl.glDrawElements(GL10.GL_TRIANGLES, drawOrder.length, GL10.GL_UNSIGNED_BYTE, drawListBuffer);
            }
        }
    }
}
