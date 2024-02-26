package com.example.YouTube.util;

import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.exp.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
    public static Integer getProfileId(HttpServletRequest request, ProfileRole... requiredRole) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");

        if (requiredRole.length == 0) {
            return id;
        }

        for (ProfileRole profileRole : requiredRole) {
            if (role.equals(profileRole)) {
                return id;
            }
        }
        throw new ForbiddenException("Method not allowed");
    }
    }
