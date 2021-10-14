## 💡 **SQL 변수 종류**

### **리터럴 변수**

where절에 column과 비교되는 값이 상수값으로 직접 선언된 변수

```sql
SELECT * FROM TB_USER WHERE ID = "TEST";
```

### **바인드 변수**

where절에 column과 비교되는 값이 바인드변수 형태로 사용하는 변수

바인드변수의 자리에는 parameter로 넘겨지는 값들이 대체됨

```sql
SELECT * FROM TB_USER WHERE ID :=1;
```

<br>

## 💡 **바인드 변수**

- **:변수**
- PL/SQL 외부에서도 사용할 수 있는 변수
- 호스트 환경에서 생성되어 데이터를 저장하기 때문에 호스트 변수라고 한다.
- 키워드 VARIABLE을 이용하며, SQL문이나 PL/SQL블록에서도 사용 가능하다.
- PL/SQL블록이 실행된 후에도 액세스가 가능하다.

<br>

## 💡 **바인드 변수 사용 이유**

### 1. Hard Parse를 줄여 성능 향상을 위한 목적

✔ **쿼리문을 받았을 때 SQL에서의 처리 절차**

1. Parse : 동일문장찾기(여기까지 Soft Parse) ⇒ syntax(문장오류) 점검 ⇒ semantic(의미) 점검 ⇒ 실행계획 결정
2. Excute : Read or Write
3. Fetch : 결과 반환

Parse 단계에서 동일문장찾기에서 끝나고 바로 Excute 로 넘어간다.

SQL문은 Hard parsing 이 많으면 자원소모가 많아지며 실행이 느리다.

### 2. 동일한 쿼리에 WHERE절의 변수값 동적 반영

WHERE절에 계속해서 변경되는 변수 값을 동적으로 반영함

<br>

## 💡 예시

```sql
BEGIN
  SELECT SAL*12+NVL(COMM*12,0) INTO :VSAL // vsal에 값을 넣겠다. (nvl은 값이 NULL일 경우 0 넣음)
    FROM EMP
   WHERE EMPNO = 7369;
END;
/ 
  
print VSAL;
```

- SQL PLUS에서 바인드 변수를 사용하고 싶을 경우

```sql
var 변수명 변수타입;
exec :변수명 := 변수타입에 따른 초기화;
```

<br>

## 💡 **PARSE 종류**

**HARD PARSE**

- sql 구문을 수행 시 SGA - shared pool - Library Cache 영역에서 해당 구문이 존재 여부를 조회하여 **존재하지 않는 경우** 해당 구문의 문법 및 권한 검사, 테이블 및 컬럼 존재 여부 등을 수행 후 Library Cache 영역에 해당 구문을 적재함.
- 수행 시 CPU 사용량이 증가할 수 있음.

**SOFT PARSE**

- sql 구문을 수행 시 SGA - shared pool - Library Cache 영역에서 해당 구문이 존재여부를 조회하여 **존재하는 경우** 해당 구문의 파싱 트리, 실행계획 등을 재사용함.
- 재사용하기 때문에 실행 속도가 빠름.
- 처음 쿼리를 조회하고 동일한 쿼리를 다시 조회할 때 첫 번째 쿼리 속도보다 빠른 이유.
- 공백, 라인, 대소문자의 차이가 있다면 다른 구문으로 인식하여 hard parse 수행.

<br>

## 💡 **MyBatis에서 처리 변수 처리 방식**

### **#{value} - 리터럴 처리 (HARD PARSE)**

```sql
SELECT * FROM TB_USER WHERE ID = #{value}
```

```sql
// 쿼리가 preparedStatement를 사용해 생성됨
UPDATE **?** SET HIDDNE = **?** WHERE ID = **?**
```

1. 변수
2. PreparedStatement에서는 ?에 해당함
3. 내부적으로 양쪽에 작은 따옴표를 붙임

<br>

### **${value} - 바인드 처리 (SOFT PARSE)**

```sql
SELECT * FROM TB_USER WHERE ID = ${value}
```

```sql
// 상수로 대입됨
UPDATE name SET HIDDNE = a WHERE ID = b
```

1. 상수
2. 내부적으로 양쪽에 작은 따옴표를 붙이지 않음
3. SQL 인젝션 위험이 있음
4. 동적으로 테이블명 또는 컬럼명을 바꿀 때만 사용
5. ${myColumn} = #{value} 이런 식으로도 가능함