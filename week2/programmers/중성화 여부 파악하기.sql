SELECT ANIMAL_ID, NAME,
       case when SEX_UPON_INTAKE like '%Neutered%' or SEX_UPON_INTAKE like '%Spayed%' then 'O'
            else 'X'
           end as 중성화
FROM ANIMAL_INS
ORDER BY ANIMAL_ID