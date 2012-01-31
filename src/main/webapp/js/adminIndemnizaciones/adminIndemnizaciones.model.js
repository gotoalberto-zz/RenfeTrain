
Ext.define('adminIndemnizaciones.model.tipoTren',{
	extend:'Ext.data.Model',
	fields:[
		{name: 'nombre',	type:'string'},
		{name: 'id',		type:'string'}
	]
});
		
Ext.define('adminIndemnizaciones.model.indemnizacion',{
	extend:'Ext.data.Model',
	fields:[
		{name: 'idTipoTren',				type: 'int'},
		{name: 'idIndemnizacion',			type: 'int'},
		{name: 'minutosRetraso',			type: 'int'},
		{name: 'porcentaje',				type:'string'}
	]
});	