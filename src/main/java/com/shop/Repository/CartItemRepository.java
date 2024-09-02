package com.shop.Repository;

import com.shop.Dto.CartListDto;
import com.shop.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long id, Long id1);

    // 각테이블에 별명 짓기  CartItem - ci, ItemImg - im, Item i
    // CartItem에 Item테이블의 번호가 연결 되어있으므로 ci.item 함으로써
    // CartItem에 있는 Item연결 ,  ItemImg는 Item과 연결되어있으므로, join을 써야한다.
    // 현재 repository의 entity는 CartItem이므로
    // CartItem과 Item은 join 하지 않아도 된다.
    @Query("select new com.shop.Dto.CartListDto( ci.id, i.itemName, " +
            "im.imgUrl, i.price , ci.quantity) " +
            "from CartItem ci , ItemImg im join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repImgYn = 'Y' " +
            "order by ci.regTime desc")
    List<CartListDto> findList(@Param("cartId") Long id);
}
