package com.example.coupang.repository;

import com.example.coupang.entity.OrderEntity;
import com.example.coupang.entity.OrderItemEntity;
import com.example.coupang.entity.CartEntity;
import com.example.coupang.entity.CartItemEntity;
import com.example.coupang.entity.ItemEntity;
import com.example.coupang.entity.MemberEntity;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    //특정 회원 -> 장바구니 조회
    public Optional<CartEntity> findCartByMemberId(Long id) {
        String sql = "SELECT c.* FROM cart_entity c WHERE c.member_id = :id";
        Query query = (Query) entityManager.createNativeQuery(sql, CartEntity.class);
        query.setParameter("id", id);

        return Optional.ofNullable((CartEntity) query.getSingleResult());
    }

   //특정회원 장바구니 안의 상품 목록 조회
    public List<CartItemEntity> findCartItemsByCartId(Long id) {
        String sql = "SELECT ci.* FROM cart_item_entity ci WHERE ci.cart_id = :id";
        Query query = (Query) entityManager.createNativeQuery(sql, CartItemEntity.class);
        query.setParameter("id", id);

        return query.getResultList();

    }



    //특정 회원의 주문목록 조회
    public Optional<OrderEntity> findByMemberEntityId(Long id) {
        String sql = "SELECT * FROM order_entity WHERE member_id = :id";
        Query query = (Query) entityManager.createNativeQuery(sql, OrderEntity.class);
        query.setParameter("id", id);

        return Optional.ofNullable((OrderEntity) query.getSingleResult());

    }
    //주문 목록 상품 리스트 반환
    public List<OrderItemEntity> findAllByOrderEntity(OrderEntity orderEntity) {
        String sql = "SELECT * FROM order_item_entity WHERE order_id = :id";
        Query query = (Query) entityManager.createNativeQuery(sql, OrderItemEntity.class);
        query.setParameter("id", orderEntity.getId());

        return query.getResultList();
    }








    //특정 회원 조회
    public Optional<MemberEntity> findMemberById(Long id) {
        String sql = "SELECT * FROM member_entity WHERE member_id = :id";

        Query query = (Query) entityManager.createNativeQuery(sql, MemberEntity.class);
        query.setParameter("id", id);

        return Optional.ofNullable((MemberEntity) query.getSingleResult());
    }

    //회원 삭제

    public void deleteById(Long id) {
        String sql = "DELETE FROM member_entity WHERE member_id = :id";

        Query query = (Query) entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }



    //회원 가입
    public void saveMember(MemberEntity memberEntity) {
        String sql = "INSERT INTO member_entity (member_email, member_name, member_password) " +
                "VALUES (:email, :name, :password)";

        Query query = (Query) entityManager.createNativeQuery(sql);
        query.setParameter("email", memberEntity.getMemberEmail());
        query.setParameter("name", memberEntity.getMemberName());
        query.setParameter("password", memberEntity.getMemberPassword());

        query.executeUpdate();
    }

    // 상품 추가
    public void addItem(ItemEntity itemEntity) {
        String sql = "INSERT INTO item_entity (item_name, item_price, item_detail) " +
                "VALUES (:itemName, :itemPrice, :itemDetail)";

        Query query = (Query) entityManager.createNativeQuery(sql);
        query.setParameter("itemName", itemEntity.getItemName());
        query.setParameter("itemPrice", itemEntity.getItemPrice());
        query.setParameter("itemDetail", itemEntity.getItemDetail());

        query.executeUpdate();
    }

    // 상품 수정
    public void updateItem(ItemEntity itemEntity) {
        String sql = "UPDATE item_entity " +
                "SET item_name = :itemName, item_price = :itemPrice, item_detail = :itemDetail " +
                "WHERE item_id = :itemId";

        Query query = (Query) entityManager.createNativeQuery(sql);
        query.setParameter("itemName", itemEntity.getItemName());
        query.setParameter("itemPrice", itemEntity.getItemPrice());
        query.setParameter("itemDetail", itemEntity.getItemDetail());
        query.setParameter("itemId", itemEntity.getId());

        query.executeUpdate();
    }

    // 상품 삭제
    public void deleteItem(Long itemId) {
        String sql = "DELETE FROM item_entity WHERE item_id = :itemId";
        Query query = (Query) entityManager.createNativeQuery(sql);
        query.setParameter("itemId", itemId);
        query.executeUpdate();
    }

    //모든 상품 조회
    public List<ItemEntity> findAllItems() {
        String sql = "SELECT * FROM item_entity";
        Query query = (Query) entityManager.createNativeQuery(sql, ItemEntity.class);
        return query.getResultList();
    }
}
