import axios from "axios";
import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { Link } from 'react-router-dom';
import style from "./style.module.css";

const BoardList = () => {
  const [boardList, setBoardList] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    axios.get('/api/board/board-list')
      .then((res) => {
        const boardData = res.data;
        console.log(boardData);
        setBoardList(boardData);
      })
      .catch((error) => {
        console.error(error);
      })
  }, []);

  const onChangeSearch = (event) => {
    event.preventDefault();
    setSearch(event.target.value);
  }

  return (
    <div>
      <div className={style.search}>
        <input className={style['search-input']} type="text" value={search} placeholder="검색" onChange={onChangeSearch} />
      </div>
      <Link to={'/board/write-form'}>
        <button className={style.write}>글 작성</button>
      </Link>

      {boardList.filter((value) => {
        if (search === "") {
          return value;
        } else if (value.title.toLowerCase().includes(search.toLowerCase())) {
          return value;
        }
      }).map((board, index) =>
        <div key={index} className={style.wrap}>
          <div>
            <Link to={`/board/board-list/` + board.boardId}>{board.title}</Link>
          </div>
          <div>{board.content}</div>
        </div>
      )}
    </div>

  );
}

export default BoardList;