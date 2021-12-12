```sql
CREATE OR REPLACE PROCEDURE PROCEDURE_NAME 
( 	
    O_RESULT OUT P_OUT_CURSOR.CURSORTYPE, 	
    V_DEPT in varchar2, 	
    V_NAME in varchar2, 	
    V_AGE in number
)
```

```xml
<?xml version="1.0" encoding="UTF-8"?> 
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 
<mapper namespace="test"> 	
<resultMap id="test" type="hashmap"></resultMap> 	 	
<select id="test" statementType="CALLABLE"> 		
{ 			
  CALL PROCEDURE_NAME( 				
      #{RESULT, mode=OUT, jdbcType=CURSOR, javaType=ResultSet, resultMap=test}, 				
      #{DEPT}, 				
      #{NAME}, 				
      #{AGE} 			
  ) 		
} 	
</select> 
</mapper>
```

1. resultMap 을 hashmap 타입으로 하나 선언해 줘야 한다. (※ id 는 다른 xml 파일에 있는 resultMap id 와 중복되면 안된다.)

2. select 엘리먼트의 statementType 을 CALLABLE 로 설정해 줘야됨.

3. 프로시져 파라메터중 IN 타입은 설정할 필요가 없지만, OUT 타입은 위와 같이 선언해 주고 resultMap 에는 위에서 설정한 resultMap의 id를 적어줘야 한다.
