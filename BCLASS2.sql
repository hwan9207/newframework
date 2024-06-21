SELECT
				  COUNT(BOARD_NO)
			FROM
				  BOARD
			WHERE
				  STATUS = 'Y'
            AND
                  BOARD_WRITER LIKE '%' || 'u' || '%'; -- 사용자가 writer 조건으로 u 라는 키워드를 검색