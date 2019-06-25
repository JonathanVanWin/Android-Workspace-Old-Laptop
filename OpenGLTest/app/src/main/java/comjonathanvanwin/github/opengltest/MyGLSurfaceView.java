package comjonathanvanwin.github.opengltest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

    private MyRenderer myRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event != null)
        {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                if (myRenderer != null)
                {
                    // Ensure we call ToggleRenderer() on the OpenGL thread.
                    // queueEvent() is a method of GLSurfaceView that will do this for us.
                    queueEvent(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            myRenderer.ToggleRenderer();
                        }
                    });

                    return true;
                }
            }
        }

        return super.onTouchEvent(event);
    }

    // Hides superclass method.
    public void setRenderer(MyRenderer renderer)
    {
        myRenderer = renderer;
        super.setRenderer(renderer);
    }
}
