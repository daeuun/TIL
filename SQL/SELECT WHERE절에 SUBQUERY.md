### 단일행 서브쿼리
```sql
SELECT USER_CD
  FROM TBL_USER
 WHERE YYMM = #{YYMM}
   AND USER_CD = (SELECT USER_CD       -- 단일행
                    FROM TBL_USER_CD
                   WHERE USER_NM = #{SRCH_USER_NM})
```
  
<br> 

### 다중컬럼 서브쿼리(Multi Column Subquery)
```sql
SELECT * 
  FROM USER
 WHERE (USER_ID, AGE) IN (SELECT USER_ID, MAX(AGE) 	
                            FROM TBL_USER 	
                           GROUP BY USER_ID) 
 ORDER BY USER_ID, USER_name;
```


<br>

## 서브쿼리(Subquery)
**하나의 SQL 문 안에 포함되어 있는 또 다른 SQL문.**  
메인쿼리가 서브쿼리를 포함하는 종속적인 관계

### 서브쿼리를 사용시 주의할 점 

1. 서브쿼리를 괄호로 감싸서 사용한다.  
2. 서브쿼리는 단일 행 또는 복수 행 비교 연산자와 함께 사용 가능  
3. 서브쿼리에서는 ORDER BY를 사용하지 못한다.

<br> 

## FROM 절에서 사용되는 서브쿼리
= 인라인 뷰(Inline View)  
FROM 절에는 테이블 명이 오도록 되어 있다.  
하지만 서브쿼리가 FROM절에 사용되면 뷰(View)처럼 결과가 동적으로 생성된 테이블로 사용할 수 있다. 
임시적인 뷰이기 때문에 데이터베이스에 저장되지는 않는다.   
인라인 뷰로 동적으로 생성된 테이블이여서 인라인 뷰의 칼럼은 자유롭게 참조가 가능하다.
 
```sql
SELECT A.DEPT_NM, B.USER_NM, B.AGE
  FROM TBL_DEPT A, (SELECT USER_ID, USER_NM, AGE	
                      FROM TBL_USER	
                     WHERE GENDER = 'F') B
 WHERE A.DEPT_CD = B.DEPT_CD;
```
