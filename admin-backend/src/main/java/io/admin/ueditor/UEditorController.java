package io.admin.ueditor;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Sunxiupeng on 2015年3月14日
 */
@Controller
@RequestMapping("/zbgl/ueditor")
public class UEditorController {

//
//    @Value("classpath:static/config/config.json")
//    private String configJSONPath;

//    @RequestMapping("/exec")
//    public @ResponseBody
//    String controller(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
//        request.setCharacterEncoding("utf-8");
//        response.setHeader("Content-Type", "text/html");
//        @SuppressWarnings("resource")
//        ApplicationContext appContext = new ClassPathXmlApplicationContext();
//       String baseState = new MyActionEnter(request, appContext.getResource(configJSONPath).getInputStream()).exec();
//
//        return baseState;
//    }
@RequestMapping(value="/exec")
public void config(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json");
    String rootPath = request.getSession().getServletContext().getRealPath("/");
    try {
        String exec = new ActionEnter(request, rootPath).exec();
        PrintWriter writer = response.getWriter();
        writer.write(exec);
        writer.flush();
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }


}
}
