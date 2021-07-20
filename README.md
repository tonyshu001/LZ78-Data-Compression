# LZ78-Data-Compression
implementation of LZ78 algorithm for text, including bit-packing
## Usage
cat text_file | java LZencode | java LZpack > compresssedData.txt

cat compresssedData.txt | java LZunpack | java LZdecode > text_file
