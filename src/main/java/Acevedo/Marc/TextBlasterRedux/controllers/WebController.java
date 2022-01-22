package Acevedo.Marc.TextBlasterRedux.controllers;

import Acevedo.Marc.TextBlasterRedux.services.GuiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    GuiService guiService;

    @Value("${admin.email}")
    private String adminEmail;

    @RequestMapping(value = "/gui", method = RequestMethod.GET)
    public String gui(@AuthenticationPrincipal OAuth2User user, Model model) {
        if (adminEmail.equals(user.getAttributes().get("email"))) {
            List<String> messageList = guiService.getMessages();
            model.addAttribute("messages", messageList);
            return "MessageBox";
        }
        return "index";
    }
}
