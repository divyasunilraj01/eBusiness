package ua.com.a1coffee.tools;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public final class ModelAndViews {

    public static void checkModelAndView(ModelAndView modelAndView, String view) {
        checkModelAndView(modelAndView, view, null);
    }

    public static void checkModelAndView(ModelAndView modelAndView, String view, String[] keys) {
        assertNotNull(modelAndView);
        assertNotNull(view);
        assertEquals(modelAndView.getViewName(), view);
        if (keys != null && keys.length > 0) {
            Map<String, Object> map = modelAndView.getModel();
            for (String key : keys) {
                assertTrue(map.containsKey(key));
                assertNotNull(map.get(key));
            }
        }
    }

    private static void assertNotNull(Object object) {
		// TODO Auto-generated method stub
		
	}

	private static void assertTrue(boolean containsKey) {
		// TODO Auto-generated method stub
		
	}

	private static void assertEquals(String viewName, String view) {
		// TODO Auto-generated method stub
		
	}

	private static void assertNotNull(String view) {
		// TODO Auto-generated method stub
		
	}

	private static void assertNotNull(ModelAndView modelAndView) {
		// TODO Auto-generated method stub
		
	}

	@Ignore
    public static void checkModelAndViewWithException(ModelAndView modelAndView) {
        String[] keys = { "cart_size", "text_error" };
        String viewName = "error/error";
        ModelAndViews.checkModelAndView(modelAndView, viewName, keys);
    }
}
