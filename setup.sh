#!/bin/bash

echo "Configuring API keys..."

# Verificar si ya existe apikeys.properties
if [ -f "./apikeys.properties" ]; then
    echo "El archivo apikeys.properties ya existe."
else
    # Crear el archivo apikeys.properties
    touch apikeys.properties
    echo "TMDB_API_KEY=tu_api_key_aqui" > apikeys.properties
    echo "Archivo apikeys.properties creado. Por favor, reemplaza 'tu_api_key_aqui' con tu clave API real."
fi