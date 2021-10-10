package org.web.helper;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.web.base.domain.exceptionservice.ResultMessageEnum;
import org.web.base.domain.exceptionservice.ServiceException;

/**
 * Created by luyl on 17-9-14.
 */
public class ExcelHelper {


    public static List<Object> parseExcel(InputStream in, String packageName) throws ServiceException {
        Map<Integer, String> columnMap = new HashMap<>();
        List<Object> objectList = new ArrayList<>();
        try {
        	Workbook workBook  = new XSSFWorkbook(in);
            Sheet sheet = workBook.getSheetAt(0);
            String classname = packageName + "." + sheet.getSheetName();
            Row row = sheet.getRow(0);
            Iterator<Cell> it = row.iterator();
            int num = 0;
            while (it.hasNext()) {
                Cell cell = it.next();
                columnMap.put(num++, cell.getRichStringCellValue().getString());
            }

            int columnNum = columnMap.keySet().size();
            int totalRows = sheet.getPhysicalNumberOfRows();
            List<Map<String, String>> list = new ArrayList<>();
            for (int i = 1; i < totalRows; i++) {
                Map<String, String> map = new HashMap<>();
                Row dataRow = sheet.getRow(i);

                for (int j = 0; j < columnNum; j++) {
                    Cell cell = dataRow.getCell(j);
                    if (cell == null) {
                        map.put(columnMap.get(j), null);
                        continue;
                    }
                    String value;
                    if (CellType.NUMERIC == cell.getCellType()) {
                        DecimalFormat df = new DecimalFormat("0");
                        value = df.format(cell.getNumericCellValue());
                    } else {
                        value = cell.toString();
                    }

                    if (value.endsWith(".0")) {
                        value = value.replace(".0", "");
                    }
                    if (StringUtils.isEmpty(value)) {
                        map.put(columnMap.get(j), null);
                    } else {
                        map.put(columnMap.get(j), value);
                    }
                }
                list.add(map);
            }

            for (Map<String, String> map : list) {
                Object obj = Class.forName(classname).newInstance();
                BeanUtils.populate(obj, map);
                objectList.add(obj);
            }

            workBook.close();
        } catch (Exception e) {
            throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.PARAM_FORMAT_INVALID);
        }
        return objectList;
    }
}
