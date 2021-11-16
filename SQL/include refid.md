# include refid
반복되는 쿼리를 미리 작성해 놓고 재활용 할 수 있게 해준다.

<br>

```sql
<mapper>  
    <sql id="test">
        id, pwd, name
    </sql>
    
    <select id="testMemberList" resultType="member">
        select 
        <include refid="test" /> --중복되는 코드 미리 작성해두고 가져온 것
        from member
    </select>
</mapper>
```

위의 쿼리문은 아래의 쿼리문을 실행한 것과 같다.

```sql
<mapper>  
    <select id="testMemberList" resultType="member">
        select id, pwd, name
        from member
    </select>
</mapper>
```
