package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ReqService;

@Controller
public class RegControl {
    private final ReqService rs;

    public RegControl(ReqService rs) {
        this.rs = rs;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (rs.userNameCheck(user.getUsername())) {
            user.setEnabled(true);
            user.setPassword(rs.getPassword(user));
            user.setAuthority(rs.getAuthority("ROLE_USER"));
            rs.save(user);
            return "redirect:/login";
        } else {
            model.addAttribute("errorMessage", "Пользователь уже существует");
            return "reg";
        }
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

}
