package com.example.demo.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.shopping.entity.Goods;
import com.example.demo.shopping.entity.vo.UpdateGoodVO;
import com.example.demo.shopping.entity.vo.UploadGoodsVO;
import com.example.demo.shopping.mapper.GoodGroupMapper;
import com.example.demo.shopping.mapper.GoodMapper;
import com.example.demo.shopping.service.GoodService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.example.demo.shopping.service.impl.UserServiceImpl.AVATAR_MAX_SIZE;

@Service
@AllArgsConstructor
@Transactional
public class GoodServiceImpl implements GoodService {

    private final GoodMapper goodMapper;
    private final GoodGroupMapper goodGroupMapper;

    @Override
    public R uploadGoods( UploadGoodsVO uploadGoodsVO) {

        // 增加商品数据
        Goods good = new Goods();
        good.setPicture(uploadGoodsVO.getPicture());
        good.setColor(uploadGoodsVO.getColor());
        good.setAddress(uploadGoodsVO.getAddress());
        good.setGroupId(uploadGoodsVO.getGroupId());
        good.setIntroduction(uploadGoodsVO.getIntroduction());
        good.setMerId(uploadGoodsVO.getMerId());
        good.setName(uploadGoodsVO.getName());
        good.setPrice(uploadGoodsVO.getPrice());
        good.setBalance(uploadGoodsVO.getBalance());
        good.setState(true);
        goodMapper.insert(good);
        return R.ok("上传成功");
    }

    /**
     * 文件上传
     */
    @Override
    public R uploadPic(MultipartFile file, Long groupId) {
        String path = "";
        String groupName = "";
        String parent = "";
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new RuntimeException("文件夹超出限制");
        }
        // 判断文件的类型是否是我们规定的和后缀类型
        String contentType = file.getContentType();

        // 如果集合包含某个元素则返回值true
        if (!UserServiceImpl.AVATAR_TYPE.contains(contentType)) {
            throw new RuntimeException("文件夹不支持");
        }


        if (groupId != null) {
            groupName = goodGroupMapper.selectById(groupId).getEName();
        }

        // 上传的文件.../upload/文件.png
        if (groupName.equals("")) {
            parent = "E:\\vue3.6.0实战\\shopping3\\public\\img\\shopping";
        } else {
            parent = "E:\\vue3.6.0实战\\shopping3\\public\\img\\shopping\\" + groupName;
        }

        // File对象指向这个路径，判断File是否存在
        File dir = new File(parent);
        if (!dir.exists()) { // 检测目录是否存在
            dir.mkdir(); //创建当前的目录
        }

        // 获取到这个文件名称,UUID工具将生成一个新的字符串作为文件名
        // 例如：avatar01.png
        String originalFilename = file.getOriginalFilename();
        // 获取.后面的数据(例.png)
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        // 避免名字重复,用UUID重新生成名字,拼接后缀
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        File dest = new File(dir, filename); //是一个空文件,文件名是filename
        // 参数file中数据写入到这个(dest)空文件中,后缀要一致
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("文件读写异常");
        }
            path = "/img/shopping/" + groupName + "/" + filename;
        return R.ok(path);
    }

    @Override
    public R changeState(Long id) {
        Goods goods = goodMapper.selectById(id);
        goods.setState(!goods.getState());
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        return R.ok(goodMapper.update(goods,wrapper));
    }

    @Override
    public R delete(Long id) {
        return R.ok(goodMapper.deleteById(id));
    }

    @Override
    public R update(UpdateGoodVO updateGoodVO) {
        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        Goods good = new Goods();
        wrapper.eq("id",updateGoodVO.getId());
        BeanUtils.copyProperties(updateGoodVO,good);
        return R.ok(goodMapper.update(good,wrapper));
    }
}
