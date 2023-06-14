package kopo.poly.service.impl;

import kopo.poly.dto.StudentDTO;
import kopo.poly.persistance.mapper.IStudentMapper;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {

    private final IStudentMapper studentMapper; // 오라클 DB와 연결된 Mapper

    @Override
    public List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".insertStudent Start!");

        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );

        if (!res.isPresent()) {
            studentMapper.insertStudent(pDTO);
        }

        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".insertStudent End!");

        return rList;
    }

    @Override
    public void insertStudentList(List<StudentDTO> pList) throws Exception {
        log.info(this.getClass().getName() + ".insertStudentList Start!");

        pList.forEach(dto -> {
            try {
                this.insertStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        log.info(this.getClass().getName() + ".insertStudentList End!");
    }

    @Override
    public void deleteStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".deleteStudent Start!");

        Optional<StudentDTO> res = Optional.ofNullable(studentMapper.getStudent(pDTO));

        if (res.isPresent()) {
            studentMapper.deleteStudent(pDTO);
            log.info(pDTO.getUserId() + "님이 삭제되었습니다.");
        } else {
            log.info("회원이 존재하지 않아 삭제하지 못했습니다.");
        }

        log.info(this.getClass().getName() + ".deleteStudent End!");
    }

    @Override
    public void deleteStudentList(List<StudentDTO> pList) throws Exception {
        log.info(this.getClass().getName() + ".deleteStudentList Start!");

        pList.forEach(dto -> {
            try {
                this.deleteStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        log.info(this.getClass().getName() + ".deleteStudentList End!");
    }

    @Override
    public void updateStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".updateStudent Start!");

        Optional<StudentDTO> res = Optional.ofNullable(studentMapper.getStudent(pDTO));

        if (res.isPresent()) {
            studentMapper.updateStudent(pDTO);
            log.info(pDTO.getUserId() + "님이 수정되었습니다.");
        } else {
            log.info("회원이 존재하지 않아 수정되지 못했습니다.");
        }

        log.info(this.getClass().getName() + ".updateStudent End!");
    }

    @Override
    public void updateStudentList(List<StudentDTO> pList) throws Exception {
        log.info(this.getClass().getName() + ".updateStudentList Start!");

        pList.forEach(dto -> {
            try {
                this.updateStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        log.info(this.getClass().getName() + ".updateStudentList End!");
    }

    @Override
    public List<StudentDTO> getStudentList() throws Exception {
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);
        return rList;
    }
}
