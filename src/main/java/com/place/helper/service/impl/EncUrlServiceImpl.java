package com.place.helper.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.place.helper.service.EncUrlService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EncUrlServiceImpl implements EncUrlService {

}
