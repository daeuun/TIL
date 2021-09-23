# selectKey

먼저 어떤 키값을 가져와서 증가시켜서 입력하거나 혹은 입력 후에 증가된 키값을 가져오고 싶을 때

<br>

### 1.입력하기전에 특정키값을 가져온 다음 그 값을 이용해서 처리하고 싶다.

만약 Board라는 게시판에서 입력을 수행시 boardID값을 기존에 최대값에 +1한 다음에 

그 값을 입력하고 싶다면 아래의 같이 처리하면 된다.

```sql
<insert id="insertBoard" parameterType="Board">
    <selectKey resultType="string" keyProperty="boardID" order="BEFORE"> -- BEFORE
        SELECT MAX(boardID)+1 FROM board        
    </selectKey>    
    INSERT INTO board(boardID, title, content)
    VALUES(#{boardID}, #{title}, #{content})
</insert>
```

### 2. 방금 입력한 값의 특정값을 리턴해주고 싶다.

```sql
<insert id="insertSurveyInfo" parameterType="Board">
    INSERT INTO board(boardID, title, content)
    VALUES(#{boarID}, #{title}, #{content})
    <selectKey resultType="int" keyProperty="iq" order="AFTER"> -- AFTER
        SELECT LAST_INSERT_ID()
    </selectKey>        
</insert>
```