package cn.lu.vblog.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SysUtils {
    public static <T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> target)
    {
        if (sourceList == null)
        {
            return null;
        }

        List targetList = new ArrayList<>(sourceList.size());
        try
        {
            for (Object source : sourceList)
            {
                T targetObject = target.newInstance();
                BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return targetList;
    }
}
