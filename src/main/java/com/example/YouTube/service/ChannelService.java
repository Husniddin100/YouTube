package com.example.YouTube.service;

import com.example.YouTube.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
}
