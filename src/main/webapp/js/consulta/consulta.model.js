
Ext.define('consulta.model.estaciones',{
	extend:'Ext.data.Model',
	fields:[
		{name: 'nombre',	type:'string'},
		{name: 'id',		type:'string'},
		{name: 'provincia',	type:'string'},
		{name: 'URL',		type:'string'}
	]
});
		
Ext.define('consulta.model.llegadas',{
	extend:'Ext.data.Model',
	fields:[
		{name: 'id',			type: 'int'},
		{name: 'hora',			type:'time'},
		{name: 'horaR',			type:'time'},
		{name: 'procedencia',	type:'string'},
		{name: 'tren',			type:'string'},
		{name: 'indemnizacion',	type:'string'}
	]
});	