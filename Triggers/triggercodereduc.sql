--Trigger qui verifie que le numero d'un ticket de reduction n'existe pas deja
create or replace trigger checkNumeroReduction
before insert or update on remiseV
FOR each ROW
declare
	num number(2);
begin
	select count(idRem) into num
	from remiseV
	where idRem = :new.idRem;
	
	if(num > 0) then
		Raise_application_error(-2010, 'On ne peut attribuer un numero qui existe deja');
	end if;
end;
/