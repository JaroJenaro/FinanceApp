package de.iav.backend.controller;

import de.iav.backend.model.UserPortfolio;
import de.iav.backend.service.UserPortfolioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/financeapp/Portfolio")
public class UserPortfolioController {
    private final UserPortfolioService userPortfolioService;

    public UserPortfolioController(UserPortfolioService userPortfolioService) {
        this.userPortfolioService = userPortfolioService;
    }

    @GetMapping("/{id}")
    public List<UserPortfolio> getUserPortfolioByUserId(@PathVariable String id){
        return userPortfolioService.makeUserPortfolio(id);
    }
}
