package yogaClassReservation;

import yogaClassReservation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserved_then_CREATE_1 (@Payload Reserved reserved) {
        try {
            if (reserved.isMe()) {
                // view 객체 생성
                MyPage myPage = new MyPage();
                // view 객체에 이벤트의 Value 를 set 함
                myPage.setId(reserved.getId());
                myPage.setReserveId(reserved.getReserveId());
                myPage.setUserId(reserved.getUserId());
                myPage.setReservationStatus("Reserved");
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserveCanceled_then_UPDATE_1(@Payload ReserveCanceled reserveCanceled) {
        try {
            if (reserveCanceled.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByReserveId(reserveCanceled.getReserveId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setReserveId(reserveCanceled.getReserveId());
                    myPage.setUserId(reserveCanceled.getUserId());
                    myPage.setReservationStatus("ReserveCanceled");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserveCanceled_then_UPDATE_1(@Payload PaymentCanceled paymentCanceled) {
        try {
            if (paymentCanceled.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByReserveId(paymentCanceled.getReserveId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setReserveId(paymentCanceled.getReserveId());
                    myPage.setUserId(paymentCanceled.getUserId());
                    myPage.setReservationStatus("PaymentCanceled");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserveCanceled_then_UPDATE_1(@Payload PaymentSucceed paymentSucceed) {
        try {
            if (paymentSucceed.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByReserveId(paymentSucceed.getReserveId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setReserveId(paymentSucceed.getReserveId());
                    myPage.setUserId(paymentSucceed.getUserId());
                    myPage.setReservationStatus("PaymentSucceed");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}