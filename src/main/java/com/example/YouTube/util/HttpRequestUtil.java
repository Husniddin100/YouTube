package com.example.YouTube.util;


import com.example.YouTube.dto.JwtDTO;
import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.exp.AppBadException;
import jakarta.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
    public static Integer getProfileId(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");

        if (requiredRoleList.length == 0) {
            return id;
        }
        for (ProfileRole requiredRole : requiredRoleList) {
            if (role.equals(requiredRole)) {
                return id;
            }
        }
        throw new AppBadException("Method not allowed");
    }

    public static JwtDTO getJWTDTO(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JwtDTO dto = new JwtDTO(id, role);
        for (ProfileRole requiredRole : requiredRoleList) {
            if (role.equals(requiredRole)) {
                return dto;
            }
        }
        throw new AppBadException("Method not allowed");
    }
}
