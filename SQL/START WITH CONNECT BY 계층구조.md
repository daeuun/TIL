```sql
SELECT 칼럼 
FROM 테이블
WHERE 조건
START WITH 최상위 조건   
CONNECT BY [PRIOR] [NOCYCLE] 
[ORDER SIBLINGS BY 칼럼, 칼럼]
```

1. START WITH : 시작 데이터를 지정한다. 
2. CONNECT BY : 계층구조에서 다음에 연결될 데이터를 지정한다. 
+ PRIOR : CONNECT BY 절에 이용되며 현재 읽은 칼럼을 지정한다.
+ PRIOR 자식 = 부모 (TOP DOWN 형태 출력)  
PRIOR 부모 = 자식 (BOTTOM UP 형태 출력)

+ ex. 부서 테이블은 parent_id에 상위 부서 정보를 갖고 있다. CONNECT BY PRIOR department_id = parent_id

+ NOCYCLE : 데이터를 펼치면서 이미 나타났던 데이터가 다시 나타나는 경우  CYCLE이 형성되었다라고 한다. 이때 오류가 발생하는데 NOCYCLE을 추가하면 사이클이 발생한 이후의 데이터를 출력하지 않는다. 

3. ORDER SIBLING BY : 조회된 데이터인 각 계층별로 정렬을 한다.  
ORDER BY 로 sort할 경우 모든 데이터를 가지고 정렬하는데, 그냥 정렬하면 계층구조가 흐트러지기 때문에 계층구조는 그대로 유지하면서 동일 부모를 가진 자식들끼리의 정렬 기준을 주는 것이다.
