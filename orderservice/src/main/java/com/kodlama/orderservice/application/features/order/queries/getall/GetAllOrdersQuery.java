package com.kodlama.orderservice.application.features.order.queries.getall;

import an.awesome.pipelinr.Command;

import java.awt.print.Pageable;
import java.util.List;

public class GetAllOrdersQuery implements Command<List<GetAllOrdersDto>>
{
    private Pageable pageable;

    public static class GettAllOrdersQueryHandler implements Command.Handler<GetAllOrdersQuery, List<GetAllOrdersDto>>
    {

        @Override
        public List<GetAllOrdersDto> handle(GetAllOrdersQuery getAllOrdersQuery) {
            return List.of();
        }
    }
}
