package org.openmall.mall.portal.oms.service.impl;

import org.openmall.mall.oms.mapper.OmsCartItemMapper;
import org.openmall.mall.oms.model.OmsCartItem;
import org.openmall.mall.oms.model.OmsCartItemExample;
import org.openmall.mall.pms.model.PmsSkuStock;
import org.openmall.mall.portal.home.dao.PortalProductDao;
import org.openmall.mall.portal.oms.domain.CartProduct;
import org.openmall.mall.portal.oms.domain.CartPromotionItem;
import org.openmall.mall.portal.oms.service.OmsCartItemService;
import org.openmall.mall.portal.oms.service.OmsPromotionService;
import org.openmall.mall.portal.ums.service.UmsMemberLoginService;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.util.MemberSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 购物车管理Service实现类
 */
@Service
public class OmsCartItemServiceImpl implements OmsCartItemService {
    @Autowired
    private OmsCartItemMapper cartItemMapper;
    @Autowired
    private PortalProductDao productDao;
    @Autowired
    private OmsPromotionService promotionService;
    @Autowired
    private UmsMemberLoginService memberService;

    private boolean matchSp(PmsSkuStock sku, OmsCartItem cartItem) {
//        List<String> listCart = makeSpList(cartItem.getSp1(), cartItem.getSp2(), cartItem.getSp3());
//        List<String> listSku = makeSpList(cartItem.getSp1(), cartItem.getSp2(), cartItem.getSp3());
//
//        int size = Math.min(listCart.size(), listSku.size());
//
//        for (int i=0;i<size;i++) {
//            if(!listCart.get(i).equals(listSku.get(i))) {
//                return false;
//            }
//        }
        return true;
    }

    private List<String> makeSpList(String sp1, String sp2, String sp3) {
        ArrayList<String> strings = new ArrayList<>();
        if(null!=sp1) strings.add(sp1);
        if(null!=sp2) strings.add(sp2);
        if(null!=sp3) strings.add(sp3);
        Collections.sort(strings);
        return strings;
    }

    @Override
    public int add(OmsCartItem cartItem) {
        int count;
        UmsMember currentMember = MemberSecurityUtil.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            CartProduct cartProduct = productDao.getCartProduct(cartItem.getProductId());

            if(null!=cartProduct){
                cartItem.setProductName(cartProduct.getName());
                cartItem.setProductPic(cartProduct.getPic());
                cartItem.setPrice(cartProduct.getPrice());
                cartItem.setProductSubTitle(cartProduct.getSubTitle());
                cartItem.setProductSn(cartProduct.getProductSn());
                cartItem.setProductBrand(cartProduct.getBrandName());
                cartItem.setProductCategoryId(cartProduct.getProductCategoryId());

                List<PmsSkuStock> skuStockList = cartProduct.getSkuStockList();
                if(null!=skuStockList) {
                    for(PmsSkuStock sku: skuStockList) {
                        if(matchSp(sku, cartItem)) {
                            cartItem.setProductSkuCode(sku.getSkuCode());
                            cartItem.setProductSkuId(sku.getId());
//                            cartItem.setSp1(sku.getSp1());
//                            cartItem.setSp2(sku.getSp2());
//                            cartItem.setSp3(sku.getSp3());
                            break;
                        }
                    }
                }

            }
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        } else {
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        }
        return count;
    }

    /**
     * 根据会员id,商品id和规格获取购物车中商品
     */
    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.createCriteria().andMemberIdEqualTo(cartItem.getMemberId())
                .andProductIdEqualTo(cartItem.getProductId()).andDeleteStatusEqualTo(0);
        if (!StringUtils.isEmpty(cartItem.getProductSkuId())) {
            criteria.andProductSkuIdEqualTo(cartItem.getProductSkuId());
        }
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }

    @Override
    public List<OmsCartItem> list(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andDeleteStatusEqualTo(0).andMemberIdEqualTo(memberId);
        return cartItemMapper.selectByExample(example);
    }

    @Override
    public List<CartPromotionItem> listPromotion(Long memberId) {
        List<OmsCartItem> cartItemList = list(memberId);
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(cartItemList)){
            cartPromotionItemList = promotionService.calcCartPromotion(cartItemList);
        }
        return cartPromotionItemList;
    }

    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setQuantity(quantity);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andDeleteStatusEqualTo(0)
                .andIdEqualTo(id).andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(cartItem, example);
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andIdIn(ids).andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public CartProduct getCartProduct(Long productId) {
        return productDao.getCartProduct(productId);
    }

    @Override
    public int updateAttr(OmsCartItem cartItem) {
        //删除原购物车信息
        OmsCartItem updateCart = new OmsCartItem();
        updateCart.setId(cartItem.getId());
        updateCart.setModifyDate(new Date());
        updateCart.setDeleteStatus(1);
        cartItemMapper.updateByPrimaryKeySelective(updateCart);
        cartItem.setId(null);
        add(cartItem);
        return 1;
    }

    @Override
    public int clear(Long memberId) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(record,example);
    }
}
