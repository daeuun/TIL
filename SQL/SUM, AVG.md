### SUM( EXPR )
EXPR의 총합을 구하는 함수

 
```sql
SELECT SUM(SALARY)   
FROM TABLE  -  
```
SALARY칼럼의 총합을 구해달라는 sql문


 

+ SUM 함수는 그룹 함수로써  
GROUB BY 절을 이용해 그룹별로 묶어 값을 출력할 수 있다.


 

 

 

### AVG( EXPR )  
EXPR의 평균을 구하는 함수

사용법은 SUM 함수와 동일하지만 총합이 아니라 평균을 구하는 점이 다른 부분

 
```sql
SELECT   AVG( SALARY )   
  FROM  TABLE  -  
```
SALARY 컬럼의 평균을 구하는 구문
: 하나의 행만 출력되며 SALARY 컬럼 모든 행의 평균을 출력


 

 

+ AVG 함수는 그룹 함수로써  
GROUB BY 절을 이용해 그룹별로 묶어 값을 출력할 수 있습니다

 


