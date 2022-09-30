package org.example;

import org.example.service.FetchUserService;

public class Main {

    public static void main(String[] args) {
        FetchUserService fetchUserService = new FetchUserService();
        fetchUserService.doFetch();
    }
}
