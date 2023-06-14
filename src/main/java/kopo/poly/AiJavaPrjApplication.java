package kopo.poly;

import kopo.poly.dto.StudentDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class AiJavaPrjApplication implements CommandLineRunner {

    // @Service 정의된 자바 파일
    // Spring Frameworks 실행될 때, @Service 정의한 자바는 자동으로 메모리에 올림
    // 메모리에 올라간 OcrService 객체를 ocrService 변수에 객체를 넣어주기
    private final IOcrService ocrService; // 이미지 인식
    private final INlpService nlpService; // 자연어 처리

    private final IStudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(AiJavaPrjApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        String filePath = "image"; // 문자열을 인식할 이미지 파일 경로
//        String fileName = "sample01.png"; // 문자열을 인식할 이미지 파일 이름
//
//        // 전달할 값(Parameter) 약자로 보통 변수명 앞에 p를 붙임 => pDTO
//        OcrDTO pDTO = new OcrDTO(); // OcrService의 함수에 정보를 전달할 DTO를 메모리에 올리기
//
//        pDTO.setFilePath(filePath);
//        pDTO.setFileName(fileName);
//
//        // 실행 결과(Result) 약자로 보통 변수명 앞에 r을 붙임 => rDTO
//        OcrDTO rDTO = ocrService.getReadforImagetext(pDTO);
//
//        String result = rDTO.getResult(); // 인식된 문자열
//
//        log.info("인식된 문자열");
//        log.info(result);
//
//        log.info("자바 프로그래밍 종료!!");
//
//        log.info("-----------------------------------------------------------------");
//        NlpDTO nlpDTO = nlpService.getPlainText(result);
//        log.info("형태소별 품사 분석 결과 : " + nlpDTO.getResult());
//
//        nlpDTO = nlpService.getNouns(result);
//
//        List<String> nouns = nlpDTO.getNouns();
//
//        Set<String> distinct = new HashSet<>(nouns);
//
//        log.info("중복 제거 수행 전 단어 수 : " + nouns.size());
//        log.info("중복 제거 수행 후 단어 수 : " + distinct.size());
//
//        Map<String, Integer> rMap = new HashMap<>();
//
//        for (String s : distinct) {
//            int count = Collections.frequency(nouns, s);
//            rMap.put(s, count);
//
//            log.info(s + " : " + count);
//        }
//
//        List<Map.Entry<String, Integer>> sortResult = new LinkedList<>(rMap.entrySet());
//
//        Collections.sort(sortResult, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
//
//        log.info("가장 많이 사용된 단어는? : " + sortResult);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        log.info("자바 프로그래밍 시작!!");

        StudentDTO pDTO;    // 학생 등록,수정,삭제에 활용될 DTO
        List<StudentDTO> rList; //DB조회 결과를 표현

//        // 학생 등록하기
//        pDTO = new StudentDTO();
//
//        pDTO.setUserId("hglee67");
//        pDTO.setUserName("이협건");
//        pDTO.setEmail("hglee67@kopo.ac.kr");
//        pDTO.setAddr("서울");
//
//        rList = studentService.insertStudent(pDTO);
//
//        loggingInfo(rList);
//
//        // 수정하기
//        pDTO = new StudentDTO();
//
//        pDTO.setUserId("hglee67");
//        pDTO.setUserName("이협건_수정");
//        pDTO.setEmail("hglee67@kopo.ac.kr_수정");
//        pDTO.setAddr("서울_수정");
//
//        studentService.updateStudent(pDTO);
//
//        rList = studentService.getStudentList();
//
//        loggingInfo(rList);
//
//        rList = studentService.getStudentList();
//
//        rList.forEach(dto -> {
//            try {
//                studentService.deleteStudent(dto);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        rList = studentService.getStudentList();

//        pDTO = new StudentDTO();
//
//        pDTO.setUserId("hglee67");
//
//        rList = studentService.deleteStudent(pDTO);
//
//        rList.forEach(dto -> {
//            log.info("DB에 저장된 아이디 : " + dto.getUserId());
//            log.info("DB에 저장된 이름 : " + dto.getUserName());
//            log.info("DB에 저장된 이메일 : " + dto.getEmail());
//            log.info("DB에 저장된 주소 : " + dto.getAddr());
//        });

        List<StudentDTO> pList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            StudentDTO allDTO = new StudentDTO();

            allDTO.setUserId("id" + (i + 1));
            allDTO.setUserName("name" + (i + 1));
            allDTO.setEmail("email" + (i + 1));
            allDTO.setAddr("addr" + (i + 1));

            pList.add(allDTO);

            pDTO = null;
        }

        pList.parallelStream().forEach(dto -> {
            try {
                studentService.insertStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        rList = studentService.getStudentList();

        loggingInfo(rList);

        rList = studentService.getStudentList();

        rList.forEach(dto -> {
            try {
                studentService.deleteStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        pList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            StudentDTO myDTO = new StudentDTO();

            myDTO.setUserId("id" + (i+1));
            myDTO.setUserName("name" + (i+1));
            myDTO.setEmail("email" + (i+1));
            myDTO.setAddr("addr" + (i+1));

            pList.add(myDTO);

            myDTO = null;
        }

        rList = studentService.getStudentList();
        loggingInfo(rList);

        studentService.insertStudentList(pList);
        rList = studentService.getStudentList();
        loggingInfo(rList);

        studentService.updateStudentList(pList);
        rList = studentService.getStudentList();
        loggingInfo(rList);

        studentService.deleteStudentList(pList);
        rList = studentService.getStudentList();
        loggingInfo(rList);


        log.info("자바 프로그래밍 종료!!");
    }

    static void loggingInfo(List<StudentDTO> pList) {
        pList.forEach(dto -> {
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름 : " + dto.getUserName());
            log.info("DB에 저장된 이메일 : " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
        });
    }
}
