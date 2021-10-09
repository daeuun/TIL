## **인덱스(Index)란?**

**데이터베이스 테이블에 대한 검색 성능의 속도를 높여주는 자료 구조**


특정 컬럼에 인덱스를 생성하면, 해당 컬럼의 데이터들을 정렬하여 별도의 메모리 공간에 **데이터의 물리적 주소와 함께 저장**된다.  
인덱스는 책에 있는 목차와 비슷하다. 책에서 정보를 찾을 때 먼저 원하는 카테고리를 목차에서 찾고 목차에 있는 페이지 번호를 보고 찾아가는 것과 비슷한 개념이다.  
인덱스에서 내가 원하는 데이터를 먼저 찾고 저장되어 있는 물리적 주소로 찾아간다.

<br>

## **인덱스(Index) 사용 예시**

**인덱스 생성**

```sql
-- 기본 문법
CREATE INDEX [인덱스명] ON [테이블명](컬럼1, 컬럼2, 컬럼3.......)

-- 예시
CREATE INDEX IDX_EX ON TB_USER(NAME, AGE);
CREATE[UNIQUE] INDEX IDX_EX ON TB_USER(NAME, AGE); --컬럼 중복값 X 예시
```

UNIQUE :  컬럼값에 중복값을 허용하지 않는다.

<br>

**인덱스 조회**

```sql
SELECT a.table_name
     , a.index_name
     , a.column_name
     , b.comments
  FROM all_ind_columns a
     , all_col_comments b
 WHERE a.table_name = 'EMP'
   AND a.table_owner = b.owner
   AND a.table_name = b.table_name
   AND a.column_name = b.column_name
 ORDER BY a.index_name
        , a.column_position

```
<br>

**인덱스 삭제**

```sql
-- 기본 문법
DROP INDEX [인덱스 명]

-- 예시
DROP INDEX IDX_EX;
```

<br>

## 인덱스(Index)를 사용하는 이유

- **조건 검색 Where 절의 효율성**

테이블을 만들고 안에 데이터가 쌓이게 되면 테이블의 레코드는 내부적으로 순서가 없이 뒤죽박죽으로 저장된다. 이렇게 되면 Where절에 특정 조건에 맞는 데이터들을 찾아낼 때, 레코드의 처음부터 끝까지 다 읽어서 검색 조건과 맞는지 비교하게 되는데 이를 `풀 테이블 스캔 (Full Table Scan)`이라고 한다.  
인덱스 테이블은 데이터들이 정렬되어 저장되어 있기 때문에 해당 조건(Where)에 맞는 데이터들을 빠르게 찾아낼 수 있다.

- **정렬 Order by 절의 효율성**

Order by에 의한 Sort 과정을 피할 수 있다. Order by는 굉장히 부하가 많이 걸리는 작업! 정렬과 동시에 1차적으로 메모리에서 정렬이 이루어지고 메모리보다 큰 작업이 필요하다면 디스크 I/O도 추가적으로 발생된다.  
인덱스를 사용하면 이미 정렬이 되어 있기 때문에 이러한 전반적인 자원의 소모를 하지 않아도 된다.

- **MIN, MAX의 효율적인 처리**

데이터가 정렬되어 있기에 얻을 수 있는 장점.  
MIN값과 MAX값을 레코드의 시작값과 끝 값 한건씩만 가져오면 되기에 FULL TABE SCAN으로 테이블을 다 뒤져서 작업하는 것보다 훨씬 효율적이다.

<br>

## **인덱스(index)의 관리**

인덱스를 항상 최신의 정렬된 상태로 유지해야 원하는 값을 빠르게 탐색할 수 있다.   
그렇기 때문에 인덱스가 적용된 컬럼에 INSERT, UPDATE, DELETE가 수행된다면 각각 다음과 같은 연산을 추가적으로 해주어야 하며 그에 따른 오버헤드가 발생한다.

- INSERT: 새로운 데이터에 대한 인덱스를 추가함
- DELETE: 삭제하는 데이터의 인덱스를 사용하지 않는다는 작업을 진행함
- UPDATE: 기존의 인덱스를 사용하지 않음 처리하고, 갱신된 데이터에 대해 인덱스를 추가함

<br>

## **인덱스(index)를 사용하면 좋은 경우**

- 규모가 작지 않은 테이블
- INSERT, UPDATE, DELETE가 자주 발생하지 않는 컬럼
- JOIN / WHERE / ORDER BY에 자주 사용되는 컬럼
- 데이터의 중복도가 낮은 컬럼 (분포도가 좋은 컬럼)
- 항상 = 으로 비교되는 컬럼