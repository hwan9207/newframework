SELECT
				  COUNT(BOARD_NO)
			FROM
				  BOARD
			WHERE
				  STATUS = 'Y'
            AND
                  BOARD_WRITER LIKE '%' || 'u' || '%'; -- ����ڰ� writer �������� u ��� Ű���带 �˻�