package com.example.demo.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.shopping.entity.Goods;
import com.example.demo.shopping.entity.Order;
import com.example.demo.shopping.entity.dto.*;
import com.example.demo.shopping.entity.vo.CheckSearchVO;
import com.example.demo.shopping.entity.vo.OrderAddVO;
import com.example.demo.shopping.entity.vo.OrderEvaluateVO;
import com.example.demo.shopping.entity.vo.OrderVO;
import com.example.demo.shopping.mapper.GoodMapper;
import com.example.demo.shopping.mapper.OrderMapper;
import com.example.demo.shopping.service.OrderService;
import com.example.demo.util.BackPage;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource
    private final OrderMapper orderMapper;
    private final GoodMapper goodMapper;

    @Override
    public R add(OrderVO orderVO) {
        List<OrderAddVO> orderAddVOSList = orderVO.getOrderAddVOS();
        Order order = new Order();
        for (OrderAddVO orderAddVO:orderAddVOSList) {
            Long goodId = orderAddVO.getGoodId();

            //减少对应商品数量
            Goods good = goodMapper.selectById(goodId);
            double balance = good.getBalance() - orderAddVO.getNum();
            if (balance <= 0) {
                try {
                    throw new RuntimeException(good.getName()+"数量不够");
                }catch (Exception e) {
                    return R.failed("对不起,"+good.getName()+"数量只剩下"+ Double.valueOf(String.valueOf(good.getBalance())).intValue() +"台,请减少数量后重新下单");
                }
            }
            good.setBalance(balance);
            goodMapper.update(good, new UpdateWrapper<Goods>().eq("id",goodId));

            OrderAddDTO orderAddDTO = goodMapper.getOrderAddDTO(goodId);
            order.setConsigneeAddress(orderVO.getOrderAddressVO().getAddress());
            order.setConsigneeName(orderVO.getOrderAddressVO().getName());
            order.setConsigneePhone(orderVO.getOrderAddressVO().getPhone());
            order.setCreateDate(getStringDate());
            order.setDealPrice(orderAddVO.getDealPrice());
            order.setGoodsId(orderAddVO.getGoodId());
            order.setGroupId(orderAddDTO.getGroupId());
            order.setMerId(orderAddDTO.getMerId());
            order.setName(orderAddVO.getName());
            order.setNum(orderAddVO.getNum());
            order.setPicture(orderAddVO.getPicture());
            order.setUnitPrice(orderAddVO.getUnitPrice());
            order.setUserId(orderAddVO.getUserId());
            order.setState("待发货");
            order.setDeleteState(0);
            orderMapper.insert(order);
        }
        return R.ok("下单成功");
    }

    @Override
    public R userList(Long userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.orderByDesc("id");
        return R.ok(orderMapper.selectList(wrapper));
    }

    @Override
    public R updateState(String state,Long id) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        return R.ok(orderMapper.update(new Order(state),wrapper));
    }

    @Override
    public R delete(Long id) {
        return R.ok(orderMapper.deleteById(id));
    }

    @Override
    public R addEvaluate(Long id, String evaluate, Integer score) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        Order order = new Order();
        order.setEvaluate(evaluate);
        order.setScore(score);
        order.setState("已评价");
        return R.ok(orderMapper.update(order,wrapper));
    }

    @Override
    public R userDelete(Long id) {
        Order order = orderMapper.selectById(id);
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        if (order.getDeleteState() == 2) {
            order.setDeleteState(3);
        } else {
            order.setDeleteState(1);
        }
        wrapper.eq("id",id);
        return R.ok(orderMapper.update(order,wrapper));
    }

    @Override
    public R changeNum(List<OrderAddVO> orderAddVOS) {
        for (OrderAddVO orderAddVO:orderAddVOS) {
            Long goodId = orderAddVO.getGoodId();
            //减少对应商品数量
            Goods good = goodMapper.selectById(goodId);
            double balance = good.getBalance() - orderAddVO.getNum();
            if (balance <= 0) {
                return R.failed("对不起," + good.getName() + "数量只剩下" + Double.valueOf(String.valueOf(good.getBalance())).intValue() + "台,请减少数量后重新下单");
            }
        }
        return R.ok("正常");
    }

    @Override
    public R meiList(Long merId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        if (merId != null) {
            wrapper.ne("delete_state",2);
            wrapper.ne("delete_state",3);
            wrapper.eq("mer_id",merId);
        }
        wrapper.orderByDesc("id");
        IPage<Order> orderIPage = new Page<>(1,10);
        return R.ok(orderMapper.selectPage(orderIPage,wrapper));
    }

    @Override
    public R merDelete(Long id) {
        Order order = orderMapper.selectById(id);
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        if (order.getDeleteState() == 1) {
            order.setDeleteState(3);
        } else {
            order.setDeleteState(2);
        }
        wrapper.eq("id",id);
        return R.ok(orderMapper.update(order,wrapper));
    }

    @Override
    public R deliver(Long id) {
        Order order = orderMapper.selectById(id);
        order.setState("已发货");
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        return R.ok(orderMapper.update(order,wrapper));
    }

    @Override
    public R checkSearch(CheckSearchVO checkSearchVO) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        if (checkSearchVO.getMerId() != null) {
            wrapper.eq("mer_id",checkSearchVO.getMerId());
        }
        if (checkSearchVO.getId() != null) {
            wrapper.like("id",checkSearchVO.getId());
        }
        if (checkSearchVO.getName() != null) {
            wrapper.like("name",checkSearchVO.getName());
        }
        if (!checkSearchVO.getGroupId().isEmpty()) {
            wrapper.in("group_id",checkSearchVO.getGroupId());
        }
        if (checkSearchVO.getState() != null) {
            wrapper.eq("state",checkSearchVO.getState());
        }
        IPage<Order> orderIPage = new Page<>(checkSearchVO.getPage(),10);
        // 封装数据，其中getRecords()是获取记录数，getCurrent()获取当前页数
        // getPages()获取总页数，getTotal()获取记录总数
        return R.ok(orderMapper.selectPage(orderIPage,wrapper));
    }

    @Override
    public R searchTime(String name) {
        OrderSearchTimeDTO searchTimeDTO = new OrderSearchTimeDTO();
        List<String> dates = new ArrayList<>();
        List<Object> obs = new ArrayList<>();
        if (name.equals("day")) {
            for (int i = 11; i >= 0; i--) {
                dates.add(getDate(i));
            }

            for (int i = 10; i >=-1; i--) {
                Object ob = orderMapper.getOrderByDay(i,"orderNum");
                if (ob != null) {
                    obs.add(ob);
                } else {
                    obs.add(0);
                }
//                obs.add(Objects.requireNonNullElse(ob, 0));
            }
            searchTimeDTO.setOrderNum(obs);

            obs = new ArrayList<>();
            for (int i = 10; i >=-1; i--) {
                Object ob = orderMapper.getOrderByDay(i,"dropShip");
                if (ob != null) {
                    obs.add(ob);
                } else {
                    obs.add(0);
                }
//                obs.add(Objects.requireNonNullElse(ob, 0));
            }
            searchTimeDTO.setDropShip(obs);

            obs = new ArrayList<>();
            for (int i = 10; i >=-1; i--) {
                Object ob = orderMapper.getOrderByDay(i,"pushDelivery");
                if (ob != null) {
                    obs.add(ob);
                } else {
                    obs.add(0);
                }
//                obs.add(Objects.requireNonNullElse(ob, 0));
            }
            searchTimeDTO.setPushDelivery(obs);

            obs = new ArrayList<>();
            for (int i = 10; i >=-1; i--) {
                Object ob = orderMapper.getOrderByDay(i,"money");
                if (ob != null) {
                    obs.add(ob);
                } else {
                    obs.add(0);
                }
//                obs.add(Objects.requireNonNullElse(ob, 0));
            }

        } else {
            for (int i = 11; i >= 0; i--) {
                String date = getDateByMonth(i);
                dates.add(date);
                obs.add(orderMapper.getOrderByMonth(date,"orderNum"));
            }
            searchTimeDTO.setOrderNum(obs);

            obs = new ArrayList<>();
            for (int i = 11; i >= 0; i--) {
                String date = getDateByMonth(i);
                obs.add(orderMapper.getOrderByMonth(date,"dropShip"));
            }
            searchTimeDTO.setDropShip(obs);

            obs = new ArrayList<>();
            for (int i = 11; i >= 0; i--) {
                String date = getDateByMonth(i);
                obs.add(orderMapper.getOrderByMonth(date,"pushDelivery"));
            }
            searchTimeDTO.setPushDelivery(obs);

            obs = new ArrayList<>();
            for (int i = 11; i >= 0; i--) {
                String date = getDateByMonth(i);
                Object ob = orderMapper.getOrderByMonth(date,"money");
                if (ob != null) {
                    obs.add(ob);
                } else {
                    obs.add(0);
                }
//                obs.add(Objects.requireNonNullElse(ob, 0));
            }
        }
        searchTimeDTO.setMoney(obs);
        searchTimeDTO.setDates(dates);
        return R.ok(searchTimeDTO);
    }

    @Override
    public R classifyNumber() {
        List<OrderClassifyNumberDTO> list = orderMapper.selectClassifyNumber();
        return R.ok(list);
    }

    @Override
    public R classifyMoney() {
        List<OrderClassifyMoneyDTO> list = orderMapper.selectClassifyMoney();
        return R.ok(list);
    }

    @Override
    public R getEvaluate(Long id) {
        List<OrderEvaluateVO> list = orderMapper.getEvaluate(id);
        return R.ok(list);
    }

    public java.sql.Date getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return java.sql.Date.valueOf(formatter.format(currentTime));
    }

    public String getDate(int i) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK,-i);
        return formatter.format(calendar.getTime());
    }

    public String getDateByMonth(int i) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,-i);
        return formatter.format(calendar.getTime());
    }
}
