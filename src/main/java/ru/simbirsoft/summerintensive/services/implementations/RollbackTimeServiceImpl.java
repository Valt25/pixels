package ru.simbirsoft.summerintensive.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbirsoft.summerintensive.models.RollbackTime;
import ru.simbirsoft.summerintensive.repository.RollbackTimeRepository;
import ru.simbirsoft.summerintensive.services.interfaces.RollbackTimeService;

@Service
public class RollbackTimeServiceImpl implements RollbackTimeService {
    @Autowired
    RollbackTimeRepository rollbackTimeRepository;

    @Override
    public long getRollbackTime() {
        if (rollbackTimeRepository.count() == 0) {
            setRollbackTime(60);
            return 60;
        }
        return rollbackTimeRepository.findAll().get(0).getTime();
    }

    @Override
    public void setRollbackTime(long time) {
        RollbackTime rb_time;
        if (rollbackTimeRepository.count() == 0) {
            rb_time = RollbackTime.builder().time(time).build();
        } else {
            rb_time = rollbackTimeRepository.findAll().get(0);
            rb_time.setTime(time);
        }
        rollbackTimeRepository.save(rb_time);
    }
}