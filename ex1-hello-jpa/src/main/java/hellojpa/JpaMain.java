package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        // Web Service가 시작할 때 한번만 작동
        // DB 하나당 하나씩 연결된다고 생각(Command Center)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // SCV 같은 개념
        // 사용자 요청에 따라 생성됨 => 절대 쓰레드간에 공유는 안된다.
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //code 작성 부
        // JPA의 모든 data 변경은 Transaction 안에서 실행되어야 한다.

        // 1. 기본 형태
//        tx.begin();
//
//        Member member = new Member();
//        member.setId(2L);
//        member.setName("BNSMOON");
        // 저장부
//        em.persist(member);
//
//        tx.commit();

//        em.close();
//        emf.close();

        // 2. try catch 문 (정석)
//        tx.begin();
//
//        try {
//            Member member = new Member();
//            member.setId(3L);
//            member.setName("CANMOON");
//            em.persist(member);
//            tx.commit();
//        }catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//        emf.close();

        // 3. spring 자동 수행(수정 작업)
//        tx.begin();
//        try {
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("ANSMOON Update");
//            // 이때는 em.persist(member); 로 저장하지 않아도 됨.
//            tx.commit();
//        }catch(Exception e) {
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//        emf.close();

        // 조회 / JPQL 사용(객체 지향 query)
        // Entity 객체 지향 query 문법 사용
//        tx.begin();
//        try {
////            Member findMember = em.find(Member.class, 1L);
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
////                    .setFirstResult(5)
////                    .setMaxResults(8)
//                    .getResultList();
//
//            for(Member member : result) {
//                System.out.println("Id: " + member.getId());
//                System.out.println("Name: " + member.getName());
//            }
//
//            // 이때는 em.persist(member); 로 저장하지 않아도 됨.
//            tx.commit(); // 필수!
//        }catch(Exception e) {
//            tx.rollback();
//        }finally {
//            em.close(); // 자원을 다쓰면 꼭 닫아줘야 한다!!! 그래야 반영됨
//        }
//        emf.close();

        // 4. 쓰기 지연 지원
//        tx.begin();
//        try {
//            Member member1 = new Member(100L, "A");
//            Member member2 = new Member(200L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("+++++++++++++++");
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();

        // 5. Entity 수정 [dirty checking]
//        tx.begin();
//        try {
//            //jpa 는 값이 바뀌면 transaction이 commit 하는 순간 값이 변경된다.
//            Member findMember = em.find(Member.class, 100L);
//            findMember.setName("150 변경");
//
//            // 왜 영속성을 부여하지 않아도 되는지?
//            // 실제 객체를 다룰 때 처럼 값 변경이후 당연하게도 저장이 따로 필요없다.
//            // 마치 collection에서 객체 값을 바꾼 것처럼
////            em.persist(findMember);
//
//            System.out.println("+++++++++++++++");
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();

        // 5. Flush
        tx.begin();
        try {
            //jpa 는 값이 바뀌면 transaction이 commit 하는 순간 값이 변경된다.
            Member member = new Member(300L, "300번째 사람이지롱");
            em.persist(member);

            em.flush(); // => 직접 호출
            System.out.println("+++++++++++++++");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}