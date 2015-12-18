package com.cloudlanes.vaultaccess;


import java.io.IOException;
import java.io.File; 
import java.io.FileInputStream; 
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

public class VaultAzureImpl implements VaultInterface{

	@Override
	public void uploadTape() {
		String AccountName="cloudlanesazure";
		String AccountKey="jYhfT3TgEJCCh3FSUf0vfhFTiU27m5TcomiPezASq+/hDoNilaGAmqZsrxF6xBTSZhinh8J3386nsH/gSSrQXQ==";
		String filepath="C:\\Upload data";
		String storageConnectionString = "DefaultEndpointsProtocol=http;" + "AccountName=" +AccountName + ";" + "AccountKey=" + AccountKey + ";";

		try {
			// connect with the Azure Storage Account and Container
			CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
			CloudBlobClient serviceClient = account.createCloudBlobClient();
			/*CloudBlobContainer container = serviceClient.getContainerReference(prop.getProperty("stgContainer"));*/
			CloudBlobContainer container = serviceClient.getContainerReference("cloudlanescontainer");

			// Create an array of data objects to upload.
			File dir = new File(filepath);
			File[] directoryListing = dir.listFiles();

			if (directoryListing != null) {
				for (File child : directoryListing) {
					// get file reference
					FileInputStream fs = new FileInputStream( child);
					File sourceFile = new File(child.getAbsolutePath());

					// set counters
					long fileSize = (long)sourceFile.length();
					int blockSize = 1024*4* 1024; // 4MB
					int blockCount = (int)((float)fileSize / (float)blockSize) + 1;
					long bytesLeft = fileSize;
					int blockNumber = 0;
					long bytesRead = 0;

					// get ref to the blob we are creating while uploading
					CloudBlockBlob blob = container.getBlockBlobReference("TapeID1/"+ sourceFile.getName() );

					// list of all block ids we will be uploading - need it for the commit at the end
					List<BlockEntry> blockList = new ArrayList<BlockEntry>();

					// loop through the file and upload chunks of the file to the blob
					while( bytesLeft > 0 ) {
						blockNumber++;

						// how much to read (only last chunk may be smaller)
						int bytesToRead = 0;
						if ( bytesLeft >= (long)blockSize ) {
							bytesToRead = blockSize;
						} else {
							bytesToRead = (int)bytesLeft;
						}

						// trace out progress
						float pctDone = ((float)blockNumber / (float)blockCount) * (float)100;
						System.out.println( "blockId: " + blockNumber + ". " + String.format("%.0f%%",pctDone) + " done.");      

						// save block id in array (must be base64)
						String blockId = DatatypeConverter.printBase64Binary(String.format("BlockId%07d", blockNumber).getBytes(StandardCharsets.UTF_8));
						BlockEntry block = new BlockEntry(blockId);
						blockList.add(block);

						// upload block chunk to Azure Storage
						blob.uploadBlock( blockId, fs, (long)bytesToRead);

						// increment/decrement counters          
						bytesRead += bytesToRead;
						bytesLeft -= bytesToRead;
					}
					fs.close();
					System.out.println("CommitBlockList. BytesUploaded: " + bytesRead);
					blob.commitBlockList(blockList);
					System.out.println("File "+sourceFile.getName()+"uploaded successfully");
				} 
			}
		}
		catch (StorageException storageException) {
			System.out.println("StorageException encountered: ");
			System.out.println(storageException.getMessage());
			System.exit(-1);
		} catch( IOException ex ) {
			System.out.println( "IOException: " + ex );
			System.exit(-1);
		} catch (Exception e) {
			System.out.println("Exception encountered: ");
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
