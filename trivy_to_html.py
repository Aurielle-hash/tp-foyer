import json
from json2html import json2html
import sys

# Vérifier les arguments pour les fichiers
if len(sys.argv) < 3:
    print("Usage: python trivy_to_html.py <input_json> <output_html>")
    sys.exit(1)

input_file = sys.argv[1]
output_file = sys.argv[2]

# Lire le fichier JSON
with open(input_file, "r") as json_file:
    data = json.load(json_file)

# Convertir les données JSON en HTML
html_content = json2html.convert(json=data)

# Ajouter un style basique pour le fichier HTML
html_template = f"""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rapport Trivy</title>
    <style>
        body {{
            font-family: Arial, sans-serif;
            margin: 20px;
        }}
        table {{
            width: 100%;
            border-collapse: collapse;
        }}
        table, th, td {{
            border: 1px solid black;
        }}
        th, td {{
            padding: 10px;
            text-align: left;
        }}
        th {{
            background-color: #f2f2f2;
        }}
    </style>
</head>
<body>
    <h1>Rapport Trivy</h1>
    {html_content}
</body>
</html>
"""

# Sauvegarder le fichier HTML
with open(output_file, "w") as html_file:
    html_file.write(html_template)

print(f"Le rapport a été converti en HTML et sauvegardé sous '{output_file}'")