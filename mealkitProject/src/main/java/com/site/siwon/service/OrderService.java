package com.site.siwon.service;

import com.site.siwon.domain.order.OrderRepository;
import com.site.siwon.web.dto.OrderSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Long save(OrderSaveRequestDto requestDto) {
        return orderRepository.save(requestDto.toEntity()).getId();
    }
}
