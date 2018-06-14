package com.example.niksior.astro.service;

import com.example.niksior.astro.data.Channel;

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}
