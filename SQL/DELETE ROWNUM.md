### DELETE ROWNUM

데이터가 너무 많아서 rownum을 지정해서 지우려고 했다.  
근데 rownum을 = 로 지정해서 지우려니까 안되는 거다..  
그래서 검색해봄

Q1.  
```sql
DELETE FROM TABLE1
WHERE ROWNUM = 1
```

하면 1개의 행이 잘 지워집니다.

```sql
DELETE FROM TABLE1
WHERE ROWNUM = 3
```

하면 0개의 행이 지워졌나고 나옵니다.. 물론 컬럼 갯수는 3개 이상입니다.

A1.  
```sql
ROWNUM <= 3
```

<br>


Q2.  
답변 감사합니다.. 그런데 제가 원하는건 3번만 삭제하는거인데.. 불가능한가요?
ROWID로는 가능하던데.. ROWNUM은 안되네요..ㅠㅠ

A2.  
```sql
DELETE
  FROM TABLE1
 WHERE ROWID = (SELECT RID
				          FROM (SELECT ROWNUM RN,
				                       ROWID RID
				                  FROM TABLE1)
				         WHERE ROWNUM = 3)
```

<br>


Q3.  
잘 해결됐습니다~ ROWNUM이 동적생성되는 성질이 있었군요..
*(동적 생성되는걸 까맣게 잊고 있었다. 으아아아아)*

A3.  
<aside>
📌 ROWNUM은 조회 결과의 행번호입니다.
행번호가 3번인 행만 가져온다면 조회결과는 1건이 되고 행번호는 다시 1이 되지요.
결국 행번호 3인 자료는 가져올 수 없습니다.

</aside>
